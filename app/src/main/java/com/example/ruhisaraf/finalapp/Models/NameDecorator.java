package com.example.ruhisaraf.finalapp.Models;

/**
 * Created by ruhisaraf on 4/12/2016.
 */
public class NameDecorator extends SearchDecorator {
    Searchable search;
    String name;

    public NameDecorator(Searchable search, String name) {
        this.search = search;
        search.user.setName(name);
    }
    @Override
    public User generateUser() {
        // TODO Auto-generated method stub
        return search.user;
    }
}
