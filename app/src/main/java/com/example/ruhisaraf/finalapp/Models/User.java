package com.example.ruhisaraf.finalapp.Models;
import android.content.Context;
import android.content.Intent;
import android.text.BoringLayout;
import android.util.Base64;

import com.example.ruhisaraf.finalapp.Activities.Login;
import com.example.ruhisaraf.finalapp.Activities.SearchResult;
import com.example.ruhisaraf.finalapp.Activities.SignUp;
import com.example.ruhisaraf.finalapp.Activities.ViewProfile;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.List;

/**
 * Created by ruhisaraf on 4/2/2016.
 */

public class User {
   @SerializedName("_id")
   Id id;
   String role;
   public String name;
   String emailID;
   String password;
   String phoneNumber;

   public User(String emailID, String password) {
       this.emailID = emailID;
       this.password = password;
   }
    public User(String name, String role, String emailID, String password) {
        this.name = name;
        this.role = role;
        this.emailID = emailID;
        this.password = password;
    }
    public User(String oid) {
        this.id = new Id();
        this.id.set$oid(oid);
    }

    public User() {

    }
    public void registerUser(Context mContext, final UserCallback userCallback) throws InterruptedException {
        ServerRequests serverRequests = new ServerRequests();
        serverRequests.createUser(this, new ServerResponseCallback(mContext, this) {
            @Override
            void onResponse(Boolean result) {
                try {
                    this.user.loginUser(mContext);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void loginUser(Context mContext) throws InterruptedException {
        ServerRequests serverRequests = new ServerRequests();
        serverRequests.getUser(this, new ServerResponseCallback(mContext) {
            @Override
            void onResponse(User user) {
                if(user != null) {
                    try {
                        viewUserProfile(mContext, user);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                }
            }
        });
    }

    public void viewUserProfile(Context mContext, User user) throws InterruptedException {
        ServerRequests serverRequests = new ServerRequests();
        serverRequests.viewUser(user, new ServerResponseCallback(mContext) {
            @Override
            void onResponse(User user) {
                if(user != null) {
                    Intent i = new Intent(mContext, ViewProfile.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("User_Name", user.getName());
                    i.putExtra("User_Email", user.getEmailID());
                    i.putExtra("User_Role", user.getRole());
                    i.putExtra("User_Password", user.getPassword());
                    i.putExtra("User_Oid", user.getId().get$oid());
                    mContext.startActivity(i);
                }
                else {
                }
            }
        });
    }

    public void updateUserProfile(Context mContext) throws InterruptedException {
        ServerRequests serverRequests = new ServerRequests();
        serverRequests.updateUser(this, new ServerResponseCallback(mContext, this) {
            @Override
            void onResponse(Boolean result) {
                if(result) {
                    try {
                        viewUserProfile(mContext, user);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                else {
                }
            }
        });
    }

    public void deleteUserProfile(Context mContext) throws InterruptedException {
        ServerRequests serverRequests = new ServerRequests();
        serverRequests.deleteUser(this, new ServerResponseCallback(mContext) {
            @Override
            void onResponse(Boolean result) {
                if(result) {
                    Intent i = new Intent(mContext, SignUp.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                }
                else {
                }
            }
        });
    }

    public void searchOtherUsers(Context mContext) {
        ServerRequests serverRequests = new ServerRequests();
        serverRequests.getMultipleUsers(this, new ServerResponseCallback(mContext) {
            @Override
            void onResponse(List<User> userList) {
                if(userList != null) {
                    System.out.println(userList.size());
                    String json = new Gson().toJson(userList);
                    System.out.println("ruhi is printing" + json);
                    Intent i = new Intent(mContext, SearchResult.class);
                    i.putExtra("NumberOfUsers", json);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                }
            }
        });
    }

    public void forgotPwd(Context mContext) throws InterruptedException {
        ServerRequests serverRequests = new ServerRequests();
        serverRequests.getUser(this, new ServerResponseCallback(mContext, this) {
            @Override
            void onResponse(User user) {
                if(user != null) try {
                    this.user = user;
                    System.out.println("In user" + user.getEmailID());
                    user.setPassword(hashUserPassword("Hello1234"));
                    ServerRequests serverRequests = new ServerRequests();
                    serverRequests.updateUser(user, new ServerResponseCallback(mContext) {
                        @Override
                        void onResponse(Boolean result) {
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                else {
                }
            }
        });
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

   /*Getters & Setters*/
   public Id getId() {
       return id;
   }
   public void setId(Id Id) {
       this.id = Id;
   }
   public String getRole() {
       return role;
   }
   public void setRole(String role) {
       this.role = role;
       System.out.println("Role: " + role );
   }
   public String getName() {
       return name;
   }
   public void setName(String name) {
       this.name = name;
       System.out.println("In set name" + this.hashCode() + this.getName());
   }
   public String getEmailID() {
       return emailID;
   }
   public void setEmailID(String emailID) {
       this.emailID = emailID;
       System.out.println("Role: " + role );
   }
   public String getPassword() {
       return password;
   }
   public void setPassword(String password) {
       this.password = password;
   }
   public String getPhoneNumber() {
       return phoneNumber;
   }
   public void setPhoneNumber(String phoneNumber) {
       this.phoneNumber = phoneNumber;
   }
  public  class Id {
        @SerializedName("$oid")
        String $oid;
        public String get$oid() {
            return $oid;
        }
        public void set$oid(String $oid) {
            this.$oid = $oid;
        }
    }
}