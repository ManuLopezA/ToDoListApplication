package com.example.toDoList.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.toDoList.Models.*;
import com.example.toDoList.Services.*;

@Controller
public class ToDoListController {

	@Autowired
	private UserService userServ;
	@Autowired
	private ToDoService toDoServ;

//	@GetMapping("/")
//	private String index(Model model) {
//		System.out.println("index route -> @GetMapping(\"/index\")");
//		model.addAttribute("toDos", toDoServ.findAll());
//		return "todo-pagination";
//	}
	
	@GetMapping("/")
	private String redirectToIndex() {
	    return "redirect:/index?page=1&size=10"; 
	}
	
	@GetMapping("/index")
	private String toDoPagination(
			@RequestParam("page") Optional<Integer> page, 
		    @RequestParam("size") Optional<Integer> size, 
			Model model)
	{
		System.out.println("toDoPagination route -> @GetMapping(\"/index\")");
		
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        
        Page<ToDo> toDoPage = toDoServ.findPaginated(PageRequest.of(currentPage - 1, pageSize));        
		model.addAttribute("toDoPage", toDoPage);
		int totalPages = toDoPage.getTotalPages();
		
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
		
		return "todo-pagination";
	}
	
	

	@GetMapping(path = "/filter-todos")
	private String filterTodos(
			@RequestParam("page") Optional<Integer> page, 
		    @RequestParam("size") Optional<Integer> size, 
			@RequestParam(value = "filterTitle", required = false) String filterTitle,
			@RequestParam(value = "filterUsername", required = false) String filterUsername, 
			Model model)
	{
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        
		List<ToDo> toDoList = toDoServ.findByTitleAndUser(filterUsername, filterTitle);
		model.addAttribute("toDos", toDoList);
		return "index";
	}

	@GetMapping("/create-todo")
	private String createOrUpdateToDo(@RequestParam(required = false) Integer id, Model model) {
	    if (id == null) {
	        model.addAttribute("users", userServ.findAllUsers());
	    } else {
	        ToDo toDo = toDoServ.getToDo(id);
	        model.addAttribute("users", userServ.findAllUsers());
	        model.addAttribute("toDo", toDo);
	    }
        return "create-todo";
	}

	@PostMapping(path = "/save-todo")
	private String saveToDo(Model model, @RequestParam int userId, @RequestParam String title,
			@RequestParam boolean completed) {
		if (title.length() > 200) {
			model.addAttribute("error", "Title must be less than 200 characters.");
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
	private String editToDo(@RequestParam int id, Model model) {
	    ToDo toDo = toDoServ.getToDo(id);
	    model.addAttribute("toDo", toDo);
	    model.addAttribute("users", userServ.findAllUsers());
	    return "create-todo";
	}


	@PostMapping("/update-todo")
	private String updateToDo(Model model, @RequestParam int id, @RequestParam int userId,
	                         @RequestParam String title, @RequestParam boolean completed) {
	    if (title.length() > 200) {
	        model.addAttribute("error", "Title must be less than 200 characters.");
	        model.addAttribute("users", userServ.findAllUsers());
	        return "create-todo";
	    }

	    ToDo toDo = toDoServ.getToDo(id);
	    if (toDo == null) {
	        model.addAttribute("error", "ToDo not found.");
	        return "redirect:/"; 
	    }

	    User user = userServ.getUser(userId);
	    toDoServ.updateToDo(toDo, user, title, completed);
	    
	    return "redirect:/"; 
	}
}
