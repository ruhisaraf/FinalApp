package com.example.ruhisaraf.finalapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.Models.UserFactory;
import com.example.ruhisaraf.finalapp.Models.UserLocalStore;
import com.example.ruhisaraf.finalapp.R;
import com.example.ruhisaraf.finalapp.Utils;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Login extends AppCompatActivity {

    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _loginButton;
    @InjectView(R.id.link_signup)
    TextView _signupLink;
    @InjectView(R.id.link_forgot_password)
    TextView _forgotpasswordLink;

    UserLocalStore userLocalStore;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);
        userLocalStore = new UserLocalStore(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _loginButton.setEnabled(false);
                email = _emailText.getText().toString();
                password = _passwordText.getText().toString();
                if (!Utils.validate(email, Login.this) || password.isEmpty() || !login()) {
                    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                    _loginButton.setEnabled(true);
                    return;
                }

            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Login.this, SignUp.class);
                startActivity(loginIntent);
            }
        });

        _forgotpasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Login.this, ForgotPassword.class);
                startActivity(loginIntent);
            }
        });


    }


    protected Boolean login() {
        UserFactory.createUser(email,Utils.hashUserPassword(password), Login.this);
        return true;
    }


}
