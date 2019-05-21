package com.pk.model;

public class SpecialNotice {
    String date, message, sender;

    public SpecialNotice() {
    }

    public SpecialNotice(String date, String message, String sender) {
        this.date = date;
        this.message = message;
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
