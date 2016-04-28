package com.example.ruhisaraf.finalapp.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ruhisaraf on 4/25/2016.
 */

public class HomeFragment extends Fragment {
    EditText _name;
    EditText _email;
    EditText _role;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.activity_view_profile, container, false);
        _name = (EditText)view.findViewById(R.id.input_name);
        _email = (EditText)view.findViewById(R.id.input_email);
        _role = (EditText)view.findViewById(R.id.input_role);


        _name.setText(getArguments().getString("User_Name"));
        _email.setText(getArguments().getString("User_Email"));
        _role.setText(getArguments().getString("User_Role"));

        return view;
    }
}
