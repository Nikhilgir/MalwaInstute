package com.example.malwainstute.lists;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.malwainstute.R;
import com.example.malwainstute.charts.Chat;
import com.example.malwainstute.login.Mit_Login;
import com.example.malwainstute.websites.Websites1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class List_of_item extends AppCompatActivity {

    FloatingActionButton btnSpeak2;
    private static String value = "";

    public static void setValue(String value) {
        List_of_item.value = value;
    }

    public static String getValue() {
        return value;
    }

    BottomNavigationView bottomNavigationView;
    MitFragment mitFragment = new MitFragment();
    RgpvFragment rgpvFragment = new RgpvFragment();
    DashboardFragment dashboardFragment=new DashboardFragment();
    protected static final int RESULT_SPEECH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_item);


        Intent i= getIntent();
        String phone1 = i.getStringExtra("phoneno");
        Bundle bundle=new Bundle();
        bundle.putString("phone",phone1);
        mitFragment.setArguments(bundle);
        dashboardFragment.setArguments(bundle);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mitFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mit:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mitFragment).commit();
                        return true;
                    case R.id.rgpv:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, rgpvFragment).commit();
                        return true;
                    case R.id.dashboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, dashboardFragment).commit();
                }
                return false;
            }
        });

// Action bar
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#771717"));
        actionBar.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);


        btnSpeak2 = findViewById(R.id.btnSpeak);

        btnSpeak2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Your device doesn't support Speech to Text", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_SPEECH) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String a = (String) text.get(0);
                switch (a) {
                    case "student login":
                    case "go to student login":
                    case "student login page": {

                        value = "http://mit.thecollegeerp.com/academic/stlogin.php";
                        Intent items = new Intent(List_of_item.this, Websites1.class);
                        startActivity(items);

                        break;
                    }
                    case "open google":
                    case "open Google":
                    case "Google": {

                        value = "https://www.google.com/";
                        Intent items = new Intent(List_of_item.this, Websites1.class);
                        startActivity(items);

                        break;
                    }
                    case "mit assistant":
                    case "MIT assistant":
                    case "go to MIT assistant": {

                        value = "http://mit.thecollegeerp.com/academic/stlogin.php";
                        Intent items = new Intent(List_of_item.this, Chat.class);
                        startActivity(items);

                        break;
                    }
                    case "logout":
                    case "go to logout":
                    case "please logout":
                    case "logout please":{

                        SharedPreferences sharedPreferences = getSharedPreferences(Mit_Login.PREFS_NAME, 0);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("hasLoggedIn", false);
                        editor.apply();
                        Intent iHome = new Intent(List_of_item.this, Mit_Login.class);
                        startActivity(iHome);
                        finish();
                        break;

                    }
                    case "teacher login":
                    case "go to teacher login":
                    case "go to staff login":
                    case "staff login":
                    case "staff login page":
                    case "teacher login page": {
                        value = "http://mit.thecollegeerp.com/academic/facultylogin.php";
                        Intent items = new Intent(List_of_item.this, Websites1.class);
                        startActivity(items);
                        break;
                    }
                    case "RGPV result":
                    case "RGPV results":
                    case "go to RGPV result":
                    case "go to RGPV results":
                    case "RGPV results page":
                    case "RGPV result page": {
                        value = "http://result.rgpv.ac.in/Result/ProgramSelect.aspx";
                        Intent items = new Intent(List_of_item.this, Websites1.class);
                        startActivity(items);
                        break;
                    }
                    case "syllabus":
                    case "download syllabus":
                    case "RGPV syllabus": {
                        value = "https://www.rgpv.ac.in/uni/frm_viewscheme.aspx";
                        Intent items = new Intent(List_of_item.this, Websites1.class);
                        startActivity(items);
                        break;
                    }
                    case "notes":
                    case "download notes":
                    case "RGPV notes": {
                        value = "https://www.rgpvnotes.in/btech/grading-system-old/notes/";
                        Intent items = new Intent(List_of_item.this, Websites1.class);
                        startActivity(items);
                        break;
                    }
                    case "notification":
                    case "exam notification":
                    case "RGPV notification": {
                        value = "https://www.rgpv.ac.in/AboutRGTU/Examination.aspx";
                        Intent items = new Intent(List_of_item.this, Websites1.class);
                        startActivity(items);
                        break;
                    }
                    case "time table":
                    case "download exam time table":
                    case "RGPV time table":
                    case "RGPV exam time table":{
                        value = "https://www.rgpv.ac.in/Uni/frm_ViewTT.aspx?id=$%";
                        Intent items = new Intent(List_of_item.this, Websites1.class);
                        startActivity(items);
                        break;
                    }

                    default:
                        Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        }
    }

}