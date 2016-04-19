package com.example.ruhisaraf.finalapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ruhisaraf.finalapp.Models.SearchViewAdapter;
import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchResult extends AppCompatActivity {

    @InjectView(R.id.searchResults) ListView _searchResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.inject(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String result = extras.getString("NumberOfUsers");
            Gson gson = new Gson();
            ArrayList<User> userList= (ArrayList<User>)gson.fromJson(result,
                    new TypeToken<ArrayList<User>>() {
                    }.getType());
            SearchViewAdapter adapter = new SearchViewAdapter(this, userList);
            _searchResults.setAdapter(adapter);
        }

    }
}
