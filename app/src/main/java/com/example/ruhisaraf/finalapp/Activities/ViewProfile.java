package com.example.ruhisaraf.finalapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.Models.UserLocalStore;
import com.example.ruhisaraf.finalapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ViewProfile extends AppCompatActivity {

    @InjectView(R.id.input_name)
    EditText _name;
    @InjectView(R.id.input_email)
    EditText _email;
    @InjectView(R.id.input_role)
    EditText _role;
    @InjectView(R.id.btn_editProfile)
    Button _editButton;
    @InjectView(R.id.btn_updateProfile)
    Button _updateButton;
    @InjectView(R.id.btn_delProfile)
    Button _deleteButton;
    @InjectView(R.id.btn_search)
    Button _searchButton;
    @InjectView(R.id.btn_logout)
    Button _logoutButton;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        String oid = null;
        User user = null;
        final User passableuser;
        final Context mContext = this.getApplicationContext();
        userLocalStore = new UserLocalStore(this);

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

        _searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(ViewProfile.this, Search.class);
                startActivity(searchIntent);
            }
        });

        _logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLocalStore.setUserLoggedIn(false);
                userLocalStore.clearUserData();
                Intent i = new Intent(mContext, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);

            }
        });
    }

}
