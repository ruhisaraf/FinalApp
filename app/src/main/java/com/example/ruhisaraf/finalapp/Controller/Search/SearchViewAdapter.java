package com.example.ruhisaraf.finalapp.Controller.Search;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.R;

import java.util.ArrayList;

public class SearchViewAdapter extends BaseAdapter {
    public ArrayList<User> users;
    Activity activity;
    TextView txtFirst;

    public SearchViewAdapter(Activity activity, ArrayList<User> users) {
        super();
        this.activity = activity;
        this.users = users;
        System.out.println("In search Adapter" + this.users.get(0).getName());
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return users.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.search_items, null);
            txtFirst = (TextView) convertView.findViewById(R.id.user_name);

        }
        String text = this.users.get(position).getName();
        System.out.println("current position " + position + " " + text);
        txtFirst.setText(text);
        return convertView;
    }
}
