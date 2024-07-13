package com.example.toDoList.Services;

import java.util.List;

import com.example.toDoList.Models.ToDo;

public interface IToDoService {	
	List<ToDo>findByTitle(String title);
	List<ToDo>findByUserName(String userName);
	List<ToDo>findByUserName(List<ToDo> toDoList, String userName);
	List<ToDo>findByTitleAndUser(String userName, String title);
	ToDo getToDo(int id);
}
