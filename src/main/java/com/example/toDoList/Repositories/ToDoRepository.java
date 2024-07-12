package com.example.toDoList.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.toDoList.Models.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer>
{
}
