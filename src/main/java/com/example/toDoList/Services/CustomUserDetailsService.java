package com.example.toDoList.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.toDoList.Models.User;
import com.example.toDoList.Utils.Color;
import com.example.toDoList.Utils.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUser(username);
		if (user == null) {
			System.err.println("USER NOT FOUND");
			throw new UsernameNotFoundException("User not found");
		}
		System.out.println(Color.GREEN + "USER FOUND" + Color.RESET);
		return new CustomUserDetails(user);
	}

	public int getLoggedInUserId() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	    int loggedInUserId = userDetails.getId();
	    System.out.println(Color.GREEN + "ID:"+loggedInUserId + " -> user logged in" + Color.RESET);
	    return loggedInUserId;
	}
}
