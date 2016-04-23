package com.example.ruhisaraf.finalapp.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruhisaraf.finalapp.Models.NameDecorator;
import com.example.ruhisaraf.finalapp.Models.RoleDecorator;
import com.example.ruhisaraf.finalapp.Models.SearchType;
import com.example.ruhisaraf.finalapp.Models.Searchable;
import com.example.ruhisaraf.finalapp.Models.SimpleSearch;
import com.example.ruhisaraf.finalapp.Models.SortUtil;
import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.R;
import com.google.gson.Gson;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Search extends AppCompatActivity {

    @InjectView(R.id.input_name) EditText _name;
    @InjectView(R.id.input_email) EditText _email;
    @InjectView(R.id.input_role) EditText _role;
    @InjectView(R.id.btn_search) Button _searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);
        final Context mContext = this.getApplicationContext();

        _searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _searchButton.setEnabled(false);

                if (!search(mContext)) {
                    Toast.makeText(getBaseContext(), "Search failed", Toast.LENGTH_LONG).show();
                    _searchButton.setEnabled(true);
                    return;
                }

            }
        });
    }

    protected Boolean search(Context mContext) {
        HashMap<String, Object> queryCriteria = new HashMap<String, Object>();
        queryCriteria.put(SearchType.NAME, _name.getText().toString());
        queryCriteria.put(SearchType.TYPE, _role.getText().toString());
        queryCriteria.put(SearchType.EMAIL,_email.getText().toString());

        Iterator<String> itr = queryCriteria.keySet().iterator();
        User user = new User();
        Searchable search = new SimpleSearch();
        while (itr.hasNext()) {
            String type = itr.next();
            if (queryCriteria.get(type) != null && !"".equals(queryCriteria.get(type))) {
                Class<?> src = null;
                try {
                    src = Class.forName("com.example.ruhisaraf.finalapp.Models." + type + "Decorator");
                    Constructor<?> con = src.getDeclaredConstructor(Searchable.class, Object.class);
                    search = (com.example.ruhisaraf.finalapp.Models.Searchable) con.newInstance(search, queryCriteria.get(type));
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
