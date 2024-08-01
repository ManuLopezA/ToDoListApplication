package com.example.toDoList.Exception;

public class CantEditException extends RuntimeException{
    public CantEditException(String message) {
        super(message);
    }
}
