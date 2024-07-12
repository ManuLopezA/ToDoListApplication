package com.example.toDoList.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.toDoList.Models.User;
import com.example.toDoList.Repositories.UserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository ur;
	
	@Override
	public List<User> findAllUsers() {		
		return ur.findAll();
	}

	@Override
	public User getUser(int id) {
		return ur.findById(id).orElse(null);
	}

}
