package com.example.ruhisaraf.finalapp.Models;

/**
 * Created by ruhisaraf on 4/12/2016.
 */
public class RoleDecorator extends SearchDecorator {
    Searchable search;
    String role;

    public RoleDecorator(Searchable search, String role) {
        this.search = search;
        search.user.setRole(role);
    }
    @Override
    public User generateUser() {
        // TODO Auto-generated method stub
        return search.user;
    }
}
