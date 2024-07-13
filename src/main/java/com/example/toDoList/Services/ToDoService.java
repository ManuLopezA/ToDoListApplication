package com.example.toDoList.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.toDoList.Models.*;
import com.example.toDoList.Repositories.*;

@Service("ToDoService")
public class ToDoService implements IToDoService {

	@Autowired
	private ToDoRepository todoRepository;
	

	public void addNewToDo(ToDo newToDo) {
		todoRepository.save(newToDo);
	}

	@Override
	public List<ToDo> findByTitle(List<ToDo>todoList, String title) {
		List<ToDo> todoListFiltered = new ArrayList<ToDo>();

		for (ToDo toDo : todoList) {
			if (toDo.getTitle().toLowerCase().contains(title.toLowerCase()))
				todoListFiltered.add(toDo);
		}
		return todoListFiltered;
	}

	@Override
	public List<ToDo> findByUserName(List<ToDo>todoList, String userName) {
		List<ToDo> todoListFiltered = new ArrayList<ToDo>();

		for (ToDo toDo : todoList) {
			if (toDo.getUser().getUserName().equals(userName))
				todoListFiltered.add(toDo);
		}
		return todoListFiltered;
	}


	@Override
	public List<ToDo> findByUserAndTitle(String userName, String title) {
		List<ToDo> todoListAll = todoRepository.findAll();
	    if (userName == null || userName.isEmpty()) {
	        return findByTitle(todoListAll, title);
	    }
	    List<ToDo> todoListNameFiltered = findByUserName(todoListAll, userName);
	    return findByTitle(todoListNameFiltered, title);
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
	
    
    public Page<ToDo> findPaginated(Pageable pageable) {
//    	System.out.println("01 findPaginated");
        return todoRepository.findAll(pageable);
    }
    
    public Page<ToDo> findPaginatedFiltered(Pageable pageable, String userName, String title ) {
//    	System.out.println("02 findPaginatedFiltered");
    	List<ToDo> todosFiltered = findByUserAndTitle(userName, title);
    	if (pageable.getSort().isSorted()) {
            Sort sort = pageable.getSort();
            todosFiltered.sort((todo1, todo2) -> {
                for (Sort.Order order : sort) {
                    int comparison = 0;
                    if ("title".equals(order.getProperty())) {
                        comparison = todo1.getTitle().compareToIgnoreCase(todo2.getTitle());
                    } else if ("username".equals(order.getProperty())) {
                        comparison = todo1.getUser().getUserName().compareToIgnoreCase(todo2.getUser().getUserName());
                    } else if ("completed".equals(order.getProperty())) {
                        comparison = Boolean.compare(todo1.isCompleted(), todo2.isCompleted());
                    }
                    if (order.isDescending()) {
                        comparison *= -1;
                    }
                    if (comparison != 0) {
                        return comparison;
                    }
                }
                return 0; 
            });
        }
    	
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min(start + pageable.getPageSize(), todosFiltered.size());
        
        List<ToDo> pagedList = todosFiltered.subList(start, end);        
        Page<ToDo> toDoPage = new PageImpl<>(pagedList, pageable, todosFiltered.size());
        
        return toDoPage;
    }
}

