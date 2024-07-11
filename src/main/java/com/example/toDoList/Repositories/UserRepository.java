package com.example.toDoList.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.toDoList.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
