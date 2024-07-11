package com.example.toDoList.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.toDoList.Models.User;

@RestController
public class Controller {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mensaje", "Hola, Thymeleaf!");
        return "palabra";
    }

//    http://localhost:8080/toDoList?a=hola&b=adios
    @GetMapping(path = "/toDoList")
    public String test(@RequestParam String a, @RequestParam String b) {
        return "hola " + a + " y ";
    }
    
    
    @PostMapping(path = "/resolveDices")
    private String resolveDices(@RequestBody User user) {
        return "Hola" + user;
    }
    
}
