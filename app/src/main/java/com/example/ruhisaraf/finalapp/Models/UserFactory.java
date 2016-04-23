package com.example.ruhisaraf.finalapp.Models;

import android.content.Context;

import com.google.gson.Gson;

import java.util.HashMap;

public class UserFactory {
    public static void createUser(String emailID, Context mContext) {

        HashMap<String, String> map = new HashMap<String,String>();
        map.put("emailID", emailID);
        Gson gson = new Gson();
        String userDetails = gson.toJson(map);
        final String role;
        //System.out.println("In UserFactor" + userDetails);
        ServerRequests serverRequests = new ServerRequests();
        final ServerResponseCallback serverResponseCallback = new ServerResponseCallback(mContext){
            @Override
            void onResponse(HashMap<String, Object> map) {
                System.out.println("ROLE COMING IN::" + map.get("role"));
                String role = map.get("role").toString();
                String email = map.get("emailID").toString();
                String password = map.get("password").toString();
            if ("Student".equals(role.trim())) {
                user = new StudentUser(email, password);
                user.setRole(role);
            } else if ("Admin".equals(role.trim())) {
                user = new AdminUser(email, password);
                user.setRole(role);
            } else if ("Tutor".equals(role.trim())) {
                user = new TutorUser(email, password);
                user.setRole(role);
            } else user = null;
                try {
                    user.loginUser(mContext);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    };
        serverRequests.getUserRole(userDetails, serverResponseCallback);
    }
}
