package com.example.ruhisaraf.finalapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.Models.UserLocalStore;
import com.example.ruhisaraf.finalapp.R;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Login extends AppCompatActivity {

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;
    @InjectView(R.id.link_forgot_password) TextView _forgotpasswordLink;

    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);
        userLocalStore = new UserLocalStore(this);

        if(userLocalStore.getLoggedInUser() != null) {
            System.out.println("In Set Preferences");
            Context mcontext = this.getApplicationContext();
            User user = userLocalStore.getLoggedInUser();
            try {
                user.loginUser(mcontext);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _loginButton.setEnabled(false);

                if (validate() || !login()) {
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

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String emailPattern = getString(R.string.email_format);
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        if (email.isEmpty() || password.isEmpty() || (!matcher.matches()) ) {
            valid = false;
        }

        return valid;
    }

    public String hashUserPassword(String password) {
        MessageDigest mdSha1 = null;
        StringBuffer sb = new StringBuffer();
        try {
            mdSha1 = MessageDigest.getInstance("SHA-1");
            mdSha1.update(password.getBytes("ASCII"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] data = mdSha1.digest();
        String hex = Base64.encodeToString(data, 0, data.length, 0);
        sb.append(hex);
        String hashedPassword = sb.toString();
        return hashedPassword.substring(0, hashedPassword.length() - 2);
    }

    protected Boolean login() {
        Context loginContext = this.getApplicationContext();
        User newUser = new User();
        newUser.setEmailID(_emailText.getText().toString());
        newUser.setPassword(hashUserPassword(_passwordText.getText().toString()));
        try {
            newUser.loginUser(loginContext);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }


}
