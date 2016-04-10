package com.example.ruhisaraf.finalapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruhisaraf.finalapp.R;
import com.example.ruhisaraf.finalapp.Models.*;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignUp extends AppCompatActivity {
    /*Load all the text / button responses from the view into the activity*/
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.role_Spinner) Spinner _roleSpinner;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        userLocalStore = new UserLocalStore(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _signupButton.setEnabled(false);

                if (validate() || !signUp()) {
                    Toast.makeText(getBaseContext(), "SignUp failed", Toast.LENGTH_LONG).show();
                    _signupButton.setEnabled(true);
                    return;
                }

            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SignUp.this, Login.class);
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

    protected Boolean signUp() {
        Context signUpContext = this.getApplicationContext();
        User newUser = new User();
        newUser.setRole(String.valueOf(_roleSpinner.getSelectedItem()));
        newUser.setEmailID(_emailText.getText().toString());
        newUser.setPassword(hashUserPassword(_passwordText.getText().toString()));
        userLocalStore.storeUserData(newUser);
        userLocalStore.setUserLoggedIn(true);

        try {
            newUser.registerUser(signUpContext);

            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
