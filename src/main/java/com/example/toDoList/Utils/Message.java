package com.example.toDoList.Utils;

public class Message {
	
	public static final String CANTEDIT = "Sorry, you cannot edit a todo that you do not own.";
	public static final String CANTDELETE = "Sorry, you cannot delete a todo that you do not own.";
	public static final String DELETED = "Deleted ToDo Successfully"; 
	public static final String EDITED = "Updated ToDo Successfully";
	public static final String TOOSHORT = "Title can not be empty.";
	public static final String TOOLONG = "Title must be less than 200 characters.";
			
	private String text;
	private String color;
	private MessageType type;

	public String getText() {
		return text;
	}

	public String getColor() {
		return color;
	}

	public MessageType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "Message [text=" + text + ", color=" + color + ", type=" + type + "]";
	}
	
	public Message() {
		super();
	}
	
	public Message(MessageType type) {
		super();		
		this.type = type;
		switch (type) {
			case CANTEDIT:
				this.text = CANTEDIT;
				this.color = "alert alert-danger"; 
				break;
			case CANTDELETE:
				this.text = CANTDELETE;
				this.color = "alert alert-danger";
				break;
			case TOOSHORT:
				this.text = TOOSHORT;
				this.color = "alert alert-danger"; 
				break;
			case TOOLONG:
				this.text = TOOLONG;
				this.color = "alert alert-danger";
				break;
			case DELETED:
				this.text = DELETED;
				this.color = "alert alert-success"; 
				break;
			case EDITED:
				this.text = EDITED;
				this.color = "alert alert-success";
				break;
		}
	}
}