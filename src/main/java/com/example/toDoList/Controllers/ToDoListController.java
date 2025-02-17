package com.example.toDoList.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.toDoList.Models.ToDo;
import com.example.toDoList.Models.User;
import com.example.toDoList.Services.CustomUserDetailsService;
import com.example.toDoList.Services.ToDoService;
import com.example.toDoList.Services.UserService;
import com.example.toDoList.Utils.Message;
import com.example.toDoList.Utils.MessageFactory;
import com.example.toDoList.Utils.MessageType;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ToDoListController {

    @Autowired
    private UserService userServ;
    @Autowired
    private ToDoService toDoServ;
    @Autowired
    private CustomUserDetailsService sessionServ;

    @GetMapping("/")
    private String redirectToIndex() {
        return "redirect:/index?page=1&size=10";
    }

    @GetMapping("/index")
    private String toDoPagination(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam(value = "filterTitle", required = false) String filterTitle,
            @RequestParam(value = "filterUsername", required = false) String filterUsername,
            @RequestParam(defaultValue = "id,asc") String[] sort,
            Model model,
            @RequestParam(required = false) Message message) {
     
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        String sortField = sort[0];
        String sortDirection = sort[1];
        
        Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Order order = new Order(direction, sortField);

        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize, Sort.by(order));
        Page<ToDo> toDoPage;

        if ((filterTitle == null || filterTitle.isEmpty()) && (filterUsername == null || filterUsername.isEmpty())) {
            toDoPage = toDoServ.findPaginated(pageRequest);
        } else {
            toDoPage = toDoServ.findPaginatedFiltered(pageRequest, filterUsername, filterTitle);
        }

        if (message != null) {
            model.addAttribute("message", message);
        }

        model.addAttribute("toDoPage", toDoPage);
        model.addAttribute("filterTitle", filterTitle);
        model.addAttribute("filterUsername", filterUsername);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        List<Integer> pageNumbers = IntStream.rangeClosed(1, toDoPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);

        return "index";
    }

    @GetMapping("/create-todo")
    private String createOrUpdateToDo(
            @RequestParam(required = false) Integer id,
            Model model) {
        
        model.addAttribute("users", userServ.findAllUsers());

        if (id != null) {
            ToDo toDo = toDoServ.getToDo(id);
            model.addAttribute("toDo", toDo);
        }

        return "create-todo";
    }

    @PostMapping("/save-todo")
    private String saveToDo(
            @RequestParam int userId,
            @RequestParam String title,
            @RequestParam boolean completed,
            Model model) {
        
        if (title.length() > 200) {
            model.addAttribute("error", MessageFactory.createMessage(MessageType.TOOLONG));
            model.addAttribute("users", userServ.findAllUsers());
            return "create-todo";
        }
        
        if (title == null || title.trim().isEmpty()) {
            model.addAttribute("error", MessageFactory.createMessage(MessageType.TOOSHORT));
            model.addAttribute("users", userServ.findAllUsers());
            return "create-todo";
        }

        User user = userServ.getUser(userId);
        ToDo newToDo = new ToDo();
        newToDo.setUser(user);
        newToDo.setTitle(title);
        newToDo.setCompleted(completed);
        toDoServ.addNewToDo(newToDo);

        return "redirect:/";
    }

    @GetMapping("/edit-todo")
    private String editToDo(
            @RequestParam int id,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        ToDo toDo = toDoServ.getToDo(id);

        if (sessionServ.getLoggedInUserId() == toDo.getUser().getId()) {
            model.addAttribute("toDo", toDo);
            model.addAttribute("users", userServ.findAllUsers());
            return "create-todo";
        }

        redirectAttributes.addFlashAttribute("message", MessageFactory.createMessage(MessageType.CANTEDIT));
        return "redirect:/index?page=1&size=10";
    }

    @PostMapping("/update-todo")
    private String updateToDo(
            @RequestParam int id,
            @RequestParam int userId,
            @RequestParam String title,
            @RequestParam boolean completed,
            RedirectAttributes redirectAttributes) {
        
        if (title.length() > 200) {
            redirectAttributes.addFlashAttribute("error", MessageFactory.createMessage(MessageType.TOOLONG));
            return "redirect:/create-todo?id=" + id;
        }

        if (title == null || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", MessageFactory.createMessage(MessageType.TOOSHORT));
            return "redirect:/create-todo?id=" + id;
        }

        ToDo toDo = toDoServ.getToDo(id);

        if (toDo == null) {
            return "redirect:/";
        }

        User user = userServ.getUser(userId);
        toDoServ.updateToDo(toDo, user, title, completed);
        redirectAttributes.addFlashAttribute("message", MessageFactory.createMessage(MessageType.EDITED));

        return "redirect:/index?page=1&size=10";
    }

    @GetMapping("/delete-todo")
    private String deleteToDo(
            @RequestParam int id,
            RedirectAttributes redirectAttributes) {
        
        ToDo toDo = toDoServ.getToDo(id);

        if (sessionServ.getLoggedInUserId() == toDo.getUser().getId()) {
            toDoServ.deleteTodo(id);
            redirectAttributes.addFlashAttribute("message", MessageFactory.createMessage(MessageType.DELETED));
        } else {
            redirectAttributes.addFlashAttribute("message", MessageFactory.createMessage(MessageType.CANTDELETE));
        }

        return "redirect:/index?page=1&size=10";
    }
}
