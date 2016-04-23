package com.example.ruhisaraf.finalapp.Controller.Search;

import com.example.ruhisaraf.finalapp.Models.User;

public class RoleDecorator extends SearchDecorator {
    Searchable search;
    String role;

    public RoleDecorator(Searchable search, Object role) {
        this.search = search;
        this.role = role.toString();
    }
    @Override
    public User generateUser() {
        // TODO Auto-generated method stub
        search.generateUser().setRole(role);
        return search.generateUser();
    }
}
