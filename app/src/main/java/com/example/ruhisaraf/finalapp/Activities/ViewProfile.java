package com.example.ruhisaraf.finalapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ViewProfile extends AppCompatActivity {

    @InjectView(R.id.input_name) EditText _name;
    @InjectView(R.id.input_email) EditText _email;
    @InjectView(R.id.input_role) EditText _role;
    @InjectView(R.id.btn_editProfile) Button _editButton;
    @InjectView(R.id.btn_updateProfile) Button _updateButton;
    @InjectView(R.id.btn_delProfile) Button _deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        String oid = null;
        User user = null;
        final User passableuser;
        final Context mContext = this.getApplicationContext();

        ButterKnife.inject(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = new User(extras.getString("User_Oid"));
            user.setName(extras.getString("User_Name"));
            user.setEmailID(extras.getString("User_Email"));
            user.setRole(extras.getString("User_Role"));
            user.setPassword(extras.getString("User_Password"));
        }

        _name.setText(user.getName());
        _email.setText(user.getEmailID());
        _role.setText(user.getRole());
        passableuser = user;

        _editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _name.setEnabled(Boolean.TRUE);
            }
        });

        _updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               passableuser.setName(_name.getText().toString());
                try {
                    passableuser.updateUserProfile(mContext);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        _deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    passableuser.deleteUserProfile(mContext);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
