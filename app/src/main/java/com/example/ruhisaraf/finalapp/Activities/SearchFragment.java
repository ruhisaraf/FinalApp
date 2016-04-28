package com.example.ruhisaraf.finalapp.Activities;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruhisaraf.finalapp.Controller.Search.SearchType;
import com.example.ruhisaraf.finalapp.Controller.Search.Searchable;
import com.example.ruhisaraf.finalapp.Controller.Search.SimpleSearch;
import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.R;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ruhisaraf on 4/25/2016.
 */
public class SearchFragment extends Fragment {
    EditText _name;
    EditText _email;
    EditText _role;
    Button _searchButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.activity_search, container, false);

        _name = (EditText) view.findViewById(R.id.input_name);
        _email = (EditText) view.findViewById(R.id.input_email);
        _role = (EditText) view.findViewById(R.id.input_role);
        _searchButton = (Button) view.findViewById(R.id.btn_search);

        _searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _searchButton.setEnabled(false);

                if (!search(getActivity())) {
                    Toast.makeText(getActivity(), "Search failed", Toast.LENGTH_LONG).show();
                    _searchButton.setEnabled(true);
                }

            }
        });

        return view;
    }

    protected Boolean search(Context mContext) {
        HashMap<String, Object> queryCriteria = new HashMap<String, Object>();
        queryCriteria.put(SearchType.NAME, _name.getText().toString());
        queryCriteria.put(SearchType.TYPE, _role.getText().toString());
        queryCriteria.put(SearchType.EMAIL, _email.getText().toString());

        Iterator<String> itr = queryCriteria.keySet().iterator();
        User user = new User();
        Searchable search = new SimpleSearch();
        while (itr.hasNext()) {
            String type = itr.next();
            if (queryCriteria.get(type) != null && !"".equals(queryCriteria.get(type))) {
                Class<?> src = null;
                try {
                    src = Class.forName("com.example.ruhisaraf.finalapp.Controller.Search." + type + "Decorator");
                    Constructor<?> con = src.getDeclaredConstructor(Searchable.class, Object.class);
                    search = (Searchable) con.newInstance(search, queryCriteria.get(type));
                    user = search.generateUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        user.searchOtherUsers(mContext);
        return true;
    }
}
