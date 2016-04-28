package com.example.ruhisaraf.finalapp.Models;

import java.util.List;

/**
 * Created by ruhisaraf on 4/25/2016.
 */
public class Conversation {
    private List<Message> messages;
    private String userID;

    public Conversation() {

    }

    public Conversation(List<Message> messages, String userID) {
        this.messages = messages;
        this.userID = userID;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
