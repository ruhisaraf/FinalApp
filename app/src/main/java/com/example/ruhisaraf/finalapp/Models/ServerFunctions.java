package com.example.ruhisaraf.finalapp.Models;

import android.content.Context;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ServerResponseCallback {
    User user;
    String password;
    Context mContext;
    UserLocalStore userLocalStore;

    ServerResponseCallback(Context mContext) {
        this.mContext = mContext;
    }

    ServerResponseCallback(Context mContext, UserLocalStore userLocalStore) {
        this.mContext = mContext;
        this.userLocalStore = userLocalStore;
    }

    ServerResponseCallback(Context mContext, User user) {
        this.mContext = mContext;
        this.user = user;
    }

    ServerResponseCallback(Context mContext, String password) {
        this.mContext = mContext;
        this.password = password;
    }

    void onResponse(Integer number) {
    }

    ;

    void onResponse(HashMap<String, Object> map) {
    }

    ;

    void onResponse(Boolean result) {
    }

    ;

    void onResponse(User user) {
    }

    ;

    void onResponse(List<User> users) {
    }

    ;
}

class ServerRequests {
    String apiKey = "0wII3g1Q9qYCLnmb4jjFmvnxrvYY-ZIk";
    String databaseName = "ralphiestutor";
    String collectionsName = "users";
    Map<String, Object> query;
    MLabsDriver mLabsClient = MLabsServiceGenerator.createService(MLabsDriver.class);

    public void findNumberOfUsers(User user, final ServerResponseCallback callback) {
        Gson gson = new Gson();
        String userDetails = gson.toJson(user);
        query = new HashMap<>();
        query.put("q", userDetails);
        query.put("c", true);
        Call<Integer> call = mLabsClient.getNumberOfDocuments(databaseName, collectionsName, apiKey, query);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer numberUsers = response.body();
                callback.onResponse(numberUsers);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callback.onResponse(-1);
            }
        });
    }

    public void createUser(User user, final ServerResponseCallback callback){
        Call<ResponseBody> call = mLabsClient.insertDocument(databaseName, collectionsName, apiKey, user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Integer statusCode = response.code();
                Boolean result = (statusCode == 200) ? true : false;
                callback.onResponse(result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onResponse(false);
            }
        });

    }

    public void getUserRole(String userDetails, final ServerResponseCallback callback) {
        query = new HashMap<>();
        query.put("q", userDetails);
        query.put("f", "{\"_id\" : 0, \"role\" : 1,  \"emailID\" : 1,  \"password\" : 1}");
        System.out.println("In getUserRole - ServerRequests" + query.toString());

        Call<List<HashMap<String,Object>>> call = mLabsClient.getRole(databaseName, collectionsName, apiKey, query);
        call.enqueue(new Callback<List<HashMap<String,Object>>>() {
            @Override
            public void onResponse(Call<List<HashMap<String,Object>>> call, Response<List<HashMap<String,Object>>> response) {
                List<HashMap<String,Object>> returnedUser = response.body();
                callback.onResponse(returnedUser.get(0));
            }

            @Override
            public void onFailure(Call<List<HashMap<String,Object>>> call, Throwable t) {
                callback.onResponse((HashMap<String, Object>) null);
            }
        });

    }

    public void getUser(final User user, final ServerResponseCallback callback) {
        Gson gson = new Gson();
        String userDetails = gson.toJson(user);
        query = new HashMap<>();
        query.put("q", userDetails);
        System.out.println("In getUserRole - ServerRequests" + query.toString());
        //query.put("f", "{\"_id\" : 1}");

        Call<List<User>> call = mLabsClient.getDocument(databaseName, collectionsName, apiKey, query);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> returnedUser = response.body();
                if (returnedUser.size() == 1) {
                    callback.onResponse(returnedUser.get(0));
                } else callback.onResponse((User) null);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onResponse((User) null);
            }
        });
    }

    public void getMultipleUsers(final User user, final ServerResponseCallback callback) {
        Gson gson = new Gson();
        String userDetails = gson.toJson(user);
        System.out.println("empty field " + userDetails);
        query = new HashMap<>();
        query.put("q", userDetails);
        query.put("f", "{\"_id\": 1, \"name\" : 1, \"role\" : 1}");

        Call<List<User>> call = mLabsClient.getDocument(databaseName, collectionsName, apiKey, query);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> returnedUser = response.body();
                callback.onResponse(returnedUser);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onResponse((List<User>) null);
            }
        });
    }

    public void viewUser(final User user, final ServerResponseCallback callback) {
        String userID = user.id.get$oid();

        Call<User> call = mLabsClient.viewDocument(databaseName, collectionsName, userID, apiKey);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User returnedUser = response.body();
                callback.onResponse(returnedUser);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onResponse((User) null);
            }
        });
    }

    public void updateUser(User user, final ServerResponseCallback callback) {
        String userID = user.id.get$oid();
        Call<ResponseBody> call = mLabsClient.updateDocument(databaseName, collectionsName, userID, apiKey, user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Integer statusCode = response.code();
                Boolean result = (statusCode == 200) ? true : false;
                callback.onResponse(result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onResponse(false);
            }
        });
    }

    public void deleteUser(final User user, final ServerResponseCallback callback) {
        String userID = user.id.get$oid();

        Call<ResponseBody> call = mLabsClient.deleteDocument(databaseName, collectionsName, userID, apiKey);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Integer statusCode = response.code();
                Boolean result = (statusCode == 200) ? true : false;
                callback.onResponse(result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onResponse(false);
            }
        });
    }
}
