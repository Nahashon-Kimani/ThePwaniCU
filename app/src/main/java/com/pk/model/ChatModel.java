package com.pk.model;

public class ChatModel {
    String message, timeSent, sender;

    public ChatModel() {
    }

    public ChatModel(String message, String timeSent, String sender) {
        this.message = message;
        this.timeSent = timeSent;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
