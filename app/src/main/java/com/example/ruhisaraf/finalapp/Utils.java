package com.example.ruhisaraf.finalapp;

import android.content.Context;
import android.util.Base64;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ruhisaraf on 4/25/2016.
 */
public class Utils {

    public static boolean validate(String email, Context mContext) {
        boolean valid = true;

        String emailPattern = mContext.getString(R.string.email_format);
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        if (email.isEmpty() || (!matcher.matches())) {
            valid = false;
        }

        return valid;
    }

    public static String hashUserPassword(String password) {
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

}
