package com.example.ruhisaraf.finalapp.Models;
import android.content.Context;
import android.content.Intent;
import android.text.BoringLayout;

import com.example.ruhisaraf.finalapp.Activities.Login;
import com.example.ruhisaraf.finalapp.Activities.SignUp;
import com.example.ruhisaraf.finalapp.Activities.ViewProfile;
import com.google.gson.annotations.SerializedName;

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
    public void registerUser(Context mContext) throws InterruptedException {
        ServerRequests serverRequests = new ServerRequests();
        serverRequests.createUser(this, new ServerResponseCallback(mContext) {
            @Override
            void onResponse(Boolean result) {
                if(result) {
                    Intent i = new Intent(mContext, Login.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                }
                else {
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

    void searchOtherUsers() {}

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