package com.example.toDoList.Utils;

public class Message {
    private String text;
    private String color;
    private MessageType type;

    public Message() {
    }

    public Message(String text, String color, MessageType type) {
        this.text = text;
        this.color = color;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message [text=" + text + ", color=" + color + ", type=" + type + "]";
    }
}
