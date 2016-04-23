package com.example.ruhisaraf.finalapp.Models;

import java.util.Objects;

/**
 * Created by ruhisaraf on 4/12/2016.
 */
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
