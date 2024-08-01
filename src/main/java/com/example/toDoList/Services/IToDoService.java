package com.example.toDoList.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.toDoList.Models.ToDo;
import com.example.toDoList.Models.User;

public interface IToDoService {    
    ToDo getToDo(int id);    
    Page<ToDo> findPaginated(Pageable pageable);    
    Page<ToDo> findPaginatedFiltered(Pageable pageable, String userName, String title);    
    boolean deleteTodo(int id);    
    void addNewToDo(ToDo toDo);    
    void updateToDo(ToDo toDo, User user, String title, boolean completed);
}
