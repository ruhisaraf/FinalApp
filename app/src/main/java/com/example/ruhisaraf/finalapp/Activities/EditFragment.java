package com.example.ruhisaraf.finalapp.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.R;

/**
 * Created by ruhisaraf on 4/25/2016.
 */
public class EditFragment extends Fragment {
    EditText _name;
    EditText _email;
    EditText _role;
    Button _updateButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.activity_edit_profile, container, false);
        _name = (EditText)view.findViewById(R.id.input_name);
        _email = (EditText)view.findViewById(R.id.input_email);
        _role = (EditText)view.findViewById(R.id.input_role);
        _updateButton = (Button) view.findViewById(R.id.btn_updateProfile) ;

        String name = getArguments().getString("User_Name");
        String email = getArguments().getString("User_Email");
        String role = getArguments().getString("User_Role");
        _name.setText(name);
        _email.setText(email);
        _role.setText(role);

        _name.setEnabled(Boolean.TRUE);

        final User passableuser = new User();
        passableuser.setName(name);
        passableuser.setEmailID(email);
        passableuser.setRole(role);

        _updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passableuser.setName(_name.getText().toString());
                try {
                    passableuser.updateUserProfile(getActivity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        return view;
    }
}
