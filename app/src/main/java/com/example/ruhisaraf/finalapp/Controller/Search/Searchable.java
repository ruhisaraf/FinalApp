package com.example.ruhisaraf.finalapp.Controller.Search;

import com.example.ruhisaraf.finalapp.Models.User;

public abstract class Searchable {
    User user = new User();

    public User generateUser() {
        return user;
    }

    ;
}

abstract class SearchDecorator extends Searchable {
    public abstract User generateUser();
}