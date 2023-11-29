package com.example.malwainstute.ragister;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.malwainstute.login.Mit_Login;
import com.example.malwainstute.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {

    EditText username,department,rollNo,password;
    TextView phone;
    String username1,department1,rollNo1,password1,phone1;
    Button register;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#771717"));
        actionBar.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

//        get value from Id
        username=findViewById(R.id.username);
        department=findViewById(R.id.department);
        rollNo=findViewById(R.id.rollNo);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);


        // create the get Intent object
        Intent i= getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String phone1 = i.getStringExtra("phoneno");
        phone.setText(phone1);
//        get button by Id
        register=findViewById(R.id.register);

//         0nl1ck operation on register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//               In String
                username1=username.getText().toString();
                department1=department.getText().toString();
                rollNo1=rollNo.getText().toString();
                password1=password.getText().toString();

                if(username1.equals("")){
                    username.setError("Fill this block");
                }else if(username1.length()<6){
                    username.setError("at least 6 or more characters.");
                }else if(department1.equals("")){
                    department.setError("Fill this block");
                }else if(rollNo1.equals("")){
                    rollNo.setError("Fill this block");
                }else if(password1.equals("")){
                    password.setError("Fill this block");
                } else if(password1.length()<6){
                    password.setError("at least 6 or more characters.");
                }else if(phone1.equals("")) {
                    phone.setError("Fill this block");
                }else if(phone1.length()<10) {
                    phone.setError("at least 10 digits.");
                }else{

                    firebaseDatabase=FirebaseDatabase.getInstance();
                    reference=firebaseDatabase.getReference("datauser");

                    StoringRegisterData storingRegisterData=new StoringRegisterData(username1,department1,rollNo1,password1,phone1);
                    reference.child(phone1).setValue(storingRegisterData);
                    Toast.makeText(RegisterPage.this, "Registion Completed", Toast.LENGTH_SHORT).show();

                    Intent iHome=new Intent(RegisterPage.this, Mit_Login.class);
                    startActivity(iHome);

                }

            }
        });
    }
}