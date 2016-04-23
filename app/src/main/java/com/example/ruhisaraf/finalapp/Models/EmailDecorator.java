package com.example.ruhisaraf.finalapp.Models;

public class EmailDecorator extends SearchDecorator {
    Searchable search;
    String email;

    public EmailDecorator(Searchable search, Object email) {
        this.search = search;
        this.email = email.toString();
    }
    @Override
    public User generateUser() {
        // TODO Auto-generated method stub
        search.generateUser().setEmailID(email);
        return search.generateUser();
    }
}
