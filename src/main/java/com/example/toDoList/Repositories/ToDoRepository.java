package com.example.toDoList.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.toDoList.Models.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer>, JpaSpecificationExecutor<ToDo> {
	@Query("SELECT t FROM ToDo t WHERE (:username = '' OR t.user.userName = :username) AND (:title = '' OR t.title LIKE CONCAT('%', :title, '%'))")
	Page<ToDo> findByUserAndTitle(@Param("username") String username, @Param("title") String title, Pageable pageable);
}
