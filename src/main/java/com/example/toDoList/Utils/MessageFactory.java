package com.example.toDoList.Utils;

public class MessageFactory {

    public static Message createMessage(MessageType type) {
        switch (type) {
            case CANTEDIT:
                return new Message("Sorry, you cannot edit a todo that you do not own.", "alert alert-danger", type);
            case CANTDELETE:
                return new Message("Sorry, you cannot delete a todo that you do not own.", "alert alert-danger", type);
            case TOOSHORT:
                return new Message("Title can not be empty.", "alert alert-danger", type);
            case TOOLONG:
                return new Message("Title must be less than 200 characters.", "alert alert-danger", type);
            case DELETED:
                return new Message("Deleted ToDo Successfully", "alert alert-success", type);
            case EDITED:
                return new Message("Updated ToDo Successfully", "alert alert-success", type);
            default:
                return new Message("", "alert alert-info", type);
        }
    }
}
