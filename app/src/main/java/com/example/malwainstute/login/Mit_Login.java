package com.example.malwainstute.login;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.malwainstute.otp.Otp;
import com.example.malwainstute.R;
import com.example.malwainstute.lists.List_of_item;
import com.example.malwainstute.ragister.RegisterPage;
import com.example.malwainstute.websites.Websites1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Mit_Login extends AppCompatActivity {

    EditText username,password;
    String username1,password1;
    Button login;
    Button registerbtn;

    TextView textview;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    protected static final int RESULT_SPEECH = 1;

    public static String PREFS_NAME="MyPrefsFile";

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mit_login);


        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#771717"));
        actionBar.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);


//        get value from Id
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        textview=findViewById(R.id.textview);

//        0nl1ck operation on Login and register button
        login=findViewById(R.id.login);
        registerbtn=findViewById(R.id.registerbtn);

        Button btnSpeak = findViewById(R.id.btnSpeak);

        //        To open register page
        registerbtn.setOnClickListener(view -> {
            Intent hHome=new Intent(Mit_Login.this, Otp.class);
            startActivity(hHome);

        });

        //        Login function
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                In String
                username1=username.getText().toString();
                password1=password.getText().toString();

//                Validation
                if(username1.equals("")){
                    username.setError("Fill this block");
                }else if(password1.equals("")){
                    password.setError("Fill this block");
                }else{

                    firebaseDatabase= FirebaseDatabase.getInstance();
                    databaseReference=firebaseDatabase.getReference("datauser");

                    Query check_username=databaseReference.orderByChild("phone").equalTo(username1);
                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()){
                                String passwordCheck=snapshot.child(username1).child("password").getValue(String.class);
                                assert passwordCheck != null;
                                if(passwordCheck.equals(password1)){

//                                    Code block After login
                                    Toast.makeText(Mit_Login.this, "successfully login", Toast.LENGTH_SHORT).show();
                                    Intent chat=new Intent(Mit_Login.this, List_of_item.class);
                                    chat.putExtra("phoneno",username1);

                                    SharedPreferences sharedPreferences=getSharedPreferences(Mit_Login.PREFS_NAME,0);
                                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor=sharedPreferences.edit();

                                    editor.putBoolean("hasLoggedIn",true);
                                    editor.putString("phoneNo",username1);
                                    editor.apply();

                                    startActivity(chat);
                                    finish();


                                }else{
                                    password.setError("Wrong password");
                                }
                            }else {
                                username.setError("user does not exist");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
//                    Toast.makeText(Mit_Login.this, "Ok", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    username.setText("");
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
                String a=(String)text.get(0);
                if (a.equals("register")||a.equals("go to register page")||a.equals("register page")){
                    Intent iHome=new Intent(Mit_Login.this, Otp.class);
                    startActivity(iHome);
                }else if (a.equals("login")||a.equals("go to login page")||a.equals("login page")){
                    Toast.makeText(this, "You are at login page", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}