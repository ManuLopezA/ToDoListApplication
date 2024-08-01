package com.example.toDoList.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.toDoList.Models.ToDo;
import com.example.toDoList.Models.User;
import com.example.toDoList.Repositories.ToDoRepository;

@Service("ToDoService")
public class ToDoService implements IToDoService {

	@Autowired
	private ToDoRepository todoRepository;

	@Override
	public Page<ToDo> findPaginated(Pageable pageable) {
		return todoRepository.findAll(pageable);
	}

	@Override
	public Page<ToDo> findPaginatedFiltered(Pageable pageable, String userName, String title) {
		return todoRepository.findByUserAndTitle(userName, title, pageable);
	}

	@Override
	public ToDo getToDo(int id) {
		return todoRepository.findById(id).orElse(null);
	}

	@Override
	public void addNewToDo(ToDo toDo) {
		todoRepository.save(toDo);
	}

	@Override
	public void updateToDo(ToDo toDo, User user, String title, boolean completed) {
		toDo.setUser(user);
		toDo.setTitle(title);
		toDo.setCompleted(completed);
		todoRepository.save(toDo);
	}

	@Override
	public boolean deleteTodo(int id) {
		if (todoRepository.existsById(id)) {
			todoRepository.deleteById(id);
			return true;
		}
		return false;
	}	
}
