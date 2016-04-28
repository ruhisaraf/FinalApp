package com.example.ruhisaraf.finalapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ruhisaraf.finalapp.Models.User;
import com.example.ruhisaraf.finalapp.Models.UserLocalStore;
import com.example.ruhisaraf.finalapp.R;

import butterknife.ButterKnife;

public class UserProfile extends Activity {
    String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;


    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        menu = new String[]{"Profile", "Edit", "Delete","Search", "Logout"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_light);

        User user = null;
        final User passableuser;
        final Context mContext = this.getApplicationContext();
        userLocalStore = new UserLocalStore(this);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = new User(extras.getString("User_Oid"));
            user.setName(extras.getString("User_Name"));
            user.setEmailID(extras.getString("User_Email"));
            user.setRole(extras.getString("User_Role"));
            user.setPassword(extras.getString("User_Password"));
        }
        passableuser = user;

        Fragment detail = new HomeFragment();
        detail.setArguments(extras);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();


        dList.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {

                dLayout.closeDrawers();
                Bundle args = new Bundle();
                Fragment detail = null;

                if(position == 0){
                    detail = new HomeFragment();
                    detail.setArguments(extras);

                }
                else if(position == 1) {
                    detail = new EditFragment();
                    detail.setArguments(extras);

                }
                else if(position == 2) {
                    try {
                        passableuser.deleteUserProfile(mContext);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if(position == 3) {
                    detail = new SearchFragment();
                }
                else if (position == 4){
                    userLocalStore.setUserLoggedIn(false);
                    userLocalStore.clearUserData();
                    Intent i = new Intent(mContext, Login.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                    return;
                }
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();

            }

        });
    }


}
