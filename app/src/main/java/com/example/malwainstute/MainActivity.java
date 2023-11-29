package com.example.malwainstute;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;

import com.example.malwainstute.lists.List_of_item;
import com.example.malwainstute.login.Mit_Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#771717"));
        actionBar.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        Intent iHome=new Intent(MainActivity.this, Mit_Login.class);

        new Handler().postDelayed((Runnable) () -> {

            SharedPreferences sharedPreferences=getSharedPreferences(Mit_Login.PREFS_NAME,0);

            boolean hasLoggedIn= sharedPreferences.getBoolean("hasLoggedIn",false);

            if (hasLoggedIn){
                Intent chat=new Intent(MainActivity.this, List_of_item.class);
                startActivity(chat);
            }else {
                startActivity(iHome);
            }
            finish();
        },3000);
    }
}