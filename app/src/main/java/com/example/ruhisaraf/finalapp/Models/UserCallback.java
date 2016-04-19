package com.example.ruhisaraf.finalapp.Models;

import android.content.Context;

import java.util.List;

/**
 * Created by ruhisaraf on 4/12/2016.
 */
public abstract class UserCallback {
    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    Context mContext;

    void onResponse(Integer number){};
    void onResponse(Boolean result){};
    void onResponse(User user){};
    void onResponse(List<User> users){};
}
