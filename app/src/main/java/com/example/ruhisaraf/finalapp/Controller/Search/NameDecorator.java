package com.example.ruhisaraf.finalapp.Controller.Search;

import com.example.ruhisaraf.finalapp.Models.User;

public class NameDecorator extends SearchDecorator {
    Searchable search;
    String name;

    public NameDecorator(Searchable search, Object name) {
        this.search = search;
        this.name = name.toString();
    }
    @Override
    public User generateUser() {
        // TODO Auto-generated method stub
        search.generateUser().setName(name);
        return search.generateUser();
    }
}
