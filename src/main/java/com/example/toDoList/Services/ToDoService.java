package com.example.toDoList.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.toDoList.Models.*;
import com.example.toDoList.Repositories.*;

@Service("ToDoService")
public class ToDoService implements IToDoService {

	@Autowired
	private ToDoRepository todoRepository;
	@Autowired
	private UserRepository userRepository;
	
	public List<ToDo> findAll() {
		return todoRepository.findAll();
	}

	public void addNewToDo(ToDo newToDo) {
		todoRepository.save(newToDo);
	}

	@Override
	public List<ToDo> findByTitle(String title) {
		List<ToDo> todoListAll = findAll();
		List<ToDo> todoListFiltered = new ArrayList<ToDo>();

		for (ToDo toDo : todoListAll) {
			if (toDo.getTitle().toLowerCase().contains(title.toLowerCase()))
				todoListFiltered.add(toDo);
		}
		return todoListFiltered;
	}

	@Override
	public List<ToDo> findByUserName(String userName) {
		List<ToDo> todoListAll = findAll();
		List<ToDo> todoListFiltered = new ArrayList<ToDo>();

		for (ToDo toDo : todoListAll) {
			if (toDo.getUser().getName().equals(userName))
				todoListFiltered.add(toDo);
		}
		return todoListFiltered;
	}

	@Override	
	public List<ToDo> findByUserName(List<ToDo> toDoList, String userName) {
		List<ToDo> todoListFiltered = new ArrayList<ToDo>();

		for (ToDo toDo : toDoList) {
			if (toDo.getUser().getUserName().equals(userName))
				todoListFiltered.add(toDo);
		}
		return todoListFiltered;
	}

	@Override
	public List<ToDo> findByTitleAndUser(String userName, String title) {
		List<ToDo> todoList = findByTitle(title);
		if (userName.isEmpty())
			return todoList;
		return findByUserName(todoList, userName);
	}

	public void updateToDo(ToDo toDo, User user, String title, boolean completed) {
		toDo.setUser(user);
		toDo.setTitle(title);
		toDo.setCompleted(completed);
		todoRepository.save(toDo);
	}

	@Override
	public ToDo getToDo(int id) {
		return todoRepository.findById(id).orElse(null);
	}
	
    public Page<ToDo> getToDos(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }
    
    
    public Page<ToDo> findPaginated(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }
    
    public Page<ToDo> findPaginatedFiltered(Pageable pageable) {
    	System.out.println(todoRepository.findAll(pageable));
        return todoRepository.findAll(pageable);
    }

}
