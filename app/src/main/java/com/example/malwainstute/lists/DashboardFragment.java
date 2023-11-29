package com.example.malwainstute.lists;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.malwainstute.R;
import com.example.malwainstute.lists.List_of_item;
import com.example.malwainstute.login.Mit_Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {

    TextView name,rollno,mobile,dep;
    ImageView imgv;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dashboard, container, false);
        // Inflate the layout for this fragment
        name=view.findViewById(R.id.dash_name);
        rollno=view.findViewById(R.id.dash_roll);
        mobile=view.findViewById(R.id.dash_phone);
        dep=view.findViewById(R.id.dash_dep);
        imgv=view.findViewById(R.id.profile_img);

        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences(Mit_Login.PREFS_NAME,0);
        String phoneNo= sharedPreferences.getString("phoneNo","00000");

//        imgv.setImage;

        assert getArguments() != null;
        String phoneu=getArguments().getString("phone");


        firebaseDatabase= FirebaseDatabase.getInstance();
        assert getArguments() != null;
        databaseReference=firebaseDatabase.getReference("datauser");

        //        Login function
                    firebaseDatabase= FirebaseDatabase.getInstance();
                    databaseReference=firebaseDatabase.getReference("datauser");

                    Query check_username=databaseReference.orderByChild("phone").equalTo(phoneNo);
                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {
                                String dbname = snapshot.child(phoneNo).child("username").getValue(String.class);
                                assert dbname != null;
                                name.setText(dbname);
                            }
                            if (snapshot.exists()) {
                                String dbrollno = snapshot.child(phoneNo).child("rollno").getValue(String.class);
                                assert dbrollno != null;
                                rollno.setText(dbrollno);
                            }
                            if (snapshot.exists()) {
                                String dbdep = snapshot.child(phoneNo).child("department").getValue(String.class);
                                assert dbdep != null;
                                dep.setText(dbdep);
                            }
                            if (snapshot.exists()) {
                                String dbphone = snapshot.child(phoneNo).child("phone").getValue(String.class);
                                assert dbphone != null;
                                mobile.setText(dbphone);
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


        return view;
    }
}