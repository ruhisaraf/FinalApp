package com.example.ruhisaraf.finalapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.Models.UserLocalStore;
import com.example.ruhisaraf.finalapp.R;
import com.example.ruhisaraf.finalapp.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ForgotPassword extends AppCompatActivity {
    UserLocalStore userLocalStore;
    @InjectView(R.id.forgot_email)
    EditText _emailText;
    @InjectView(R.id.btn_forgot_pwd)
    Button _forgotPwdButton;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.inject(this);

        userLocalStore = new UserLocalStore(this);
        _forgotPwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _forgotPwdButton.setEnabled(false);
                String email = _emailText.getText().toString();
                if (Utils.validate(email, ForgotPassword.this)) {
                    userLocalStore.clearUserData();
                    Toast.makeText(getBaseContext(), "If Your EmailId exists in the system, you will receive a new password", Toast.LENGTH_LONG).show();
                    User user = new User();
                    user.setEmailID(_emailText.getText().toString());
                    try {
                        user.forgotPwd(ForgotPassword.this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getBaseContext(), "Enter Valid EmailID", Toast.LENGTH_LONG).show();
                    _forgotPwdButton.setEnabled(true);
                }
            }
        });
    }


}
