package com.example.toDoList.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.toDoList.Models.ToDo;
import com.example.toDoList.Repositories.ToDoRepository;

@Service("ToDoService")
public class ToDoService implements IToDoService{

	@Autowired
	ToDoRepository todoRepository;
	
	public List<ToDo> findAll(){
		return todoRepository.findAll();		
	}
	
	

	@Override
	public List<ToDo> findByTitle(String title) {		
		List<ToDo> todoListAll = findAll();
		List<ToDo> todoListFiltered = new ArrayList();
		
		for (ToDo toDo : todoListAll) {
			if(toDo.getTitle().contains(title))
				todoListFiltered.add(toDo);
		}
		return todoListFiltered;
	}

	@Override
	public List<ToDo> findByUserName(String userName) {
		List<ToDo> todoListAll = findAll();
		List<ToDo> todoListFiltered = new ArrayList();	
		
		for (ToDo toDo : todoListFiltered) {
			if(toDo.getUser().getName().equals(userName))
				todoListFiltered.add(toDo);
		}		
		return todoListFiltered;
	}
}
