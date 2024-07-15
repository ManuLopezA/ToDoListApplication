package com.example.toDoList.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	private String login() {
		return "login";
	}

	@GetMapping("/login-error")
	private String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	
//	@GetMapping("/logout")
//	public String logout() {
//		return "redirect:/login?logout=true";
//	}

//	@PostMapping("/login-in")
//	private String loginIn(@RequestParam String username, @RequestParam String password, Model model) {
//
//		if (!userServ.findByUserName(username)) {
//			model.addAttribute("loginError", true);
//			return "login";
//		}
//
//		User user = userServ.getUser(username);
//		if (!userServ.correctPassword(password, user)){
//			model.addAttribute("loginError", true);
//			return "login";
//		}
//		System.out.println(username);
//		return "redirect:/";
//	}

}
