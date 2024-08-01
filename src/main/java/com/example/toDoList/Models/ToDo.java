package com.example.toDoList.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ToDo 
{
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false, length = 199)
	private String title;
	@Column
	private boolean completed;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id", nullable = false)
	private User user;

	public ToDo() {
		super();
	}	
	
	public ToDo(String title, boolean completed, User user) {
		super();
		this.title = title;
		this.completed = completed;
		this.user = user;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "ToDo [id=" + id + ", title=" + title + ", completed=" + completed + ", user=" + user + "]";
	}

}
