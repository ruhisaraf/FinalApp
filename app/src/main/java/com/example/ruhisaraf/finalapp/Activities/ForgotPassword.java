package com.example.ruhisaraf.finalapp.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.Models.UserCallback;
import com.example.ruhisaraf.finalapp.Models.UserLocalStore;
import com.example.ruhisaraf.finalapp.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ForgotPassword extends AppCompatActivity {

    @InjectView(R.id.forgot_email)
    EditText _emailText;
    @InjectView(R.id.btn_forgot_pwd)
    Button _forgotPwdButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    UserLocalStore userLocalStore = new UserLocalStore(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.inject(this);


        _forgotPwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _forgotPwdButton.setEnabled(false);
                if (!validate()) {
                    Toast.makeText(getBaseContext(), "Enter Valid EmailID", Toast.LENGTH_LONG).show();
                    _forgotPwdButton.setEnabled(true);
                    return;
                } else {
                    userLocalStore.clearUserData();
                    Toast.makeText(getBaseContext(), "If Your EmailId exists in the system, you will receive a new password", Toast.LENGTH_LONG).show();
                    User user = new User();
                    user.setEmailID(_emailText.getText().toString());
                    try {
                        user.forgotPwd(getBaseContext());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String emailPattern = getString(R.string.email_format);
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        if (email.isEmpty() || (!matcher.matches())) {
            valid = false;
        }

        return valid;
    }


}
