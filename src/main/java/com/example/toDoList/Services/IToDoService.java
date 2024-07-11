package com.example.toDoList.Services;

import java.util.List;

import com.example.toDoList.Models.ToDo;

public interface IToDoService {	
	List<ToDo>findByTitle(String title);
	List<ToDo>findByUserName(String userName);
}
