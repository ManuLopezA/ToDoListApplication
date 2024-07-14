package com.example.toDoList.Models;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * CustomAuthenticationSuccessHandler is used to explore Spring Security's 
 * authentication process. It allows access to user details (e.g., username, 
 * authorities) after a successful login, useful for further implementation.
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println(Color.BLUE + "--------------------------");
        System.out.println("LOGIN OK");
        System.out.println("Username: " + userDetails.getUsername()); 
        userDetails.getAuthorities().forEach(authority -> {
            System.out.println("Authority: " + authority.getAuthority());
        });
		System.out.println("--------------------------"+Color.RESET);
		
		// Redirects to the homepage after successful authentication.
		response.sendRedirect("/");
	}
}
