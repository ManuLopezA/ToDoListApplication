package com.example.toDoList.Services;

import java.util.List;

import com.example.toDoList.Models.User;

public interface IUserService {
	List<User>findAllUsers();
	User getUser(int id);
	User getUser(String userName);
	boolean findByUserName(String userName);
	void addNewUser(User user);
}
