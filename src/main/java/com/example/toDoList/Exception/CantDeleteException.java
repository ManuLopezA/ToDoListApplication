package com.example.toDoList.Exception;

public class CantDeleteException extends RuntimeException{
	public CantDeleteException(String message) {
		super(message);
	}
}
