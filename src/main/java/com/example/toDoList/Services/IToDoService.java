package com.example.toDoList.Services;

import java.util.List;

import com.example.toDoList.Models.ToDo;

public interface IToDoService {	
	ToDo getToDo(int id);
	List<ToDo> findByUserName(List<ToDo> list, String userName);	
	List<ToDo> findByTitle(List<ToDo> list, String title);
	List<ToDo> findByUserAndTitle(String userName, String title);
	boolean deleteTodo(int id);
}
