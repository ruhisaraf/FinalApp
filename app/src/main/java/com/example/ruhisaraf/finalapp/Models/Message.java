package com.example.ruhisaraf.finalapp.Models;

/**
 * Created by ruhisaraf on 4/25/2016.
 */
public class Message {
    private String userMessage;
    private String userID;
    public Message() {

    }
    public Message(String userMessage, String userID){
        this.userMessage = userMessage;
        this.userID = userID;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
