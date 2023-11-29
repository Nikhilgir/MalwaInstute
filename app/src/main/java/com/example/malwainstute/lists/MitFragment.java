package com.example.malwainstute.lists;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.malwainstute.R;

import java.util.ArrayList;
import java.util.Arrays;


public class MitFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList courseImgMIT = new ArrayList<>(Arrays.asList(R.drawable.chart1, R.drawable.student,
            R.drawable.teacher, R.drawable.logout));


//////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mit, container, false);

//        if (getArguments()!=null){
//            String phone=getArguments().getString("phone");
//        }

        assert getArguments() != null;
        ArrayList courseNameMIT = new ArrayList<>(Arrays.asList("MIT Assistant", "Student Login", "Staff Login", "Log out"));
        // Getting reference of recyclerView
        recyclerView =view.findViewById(R.id.recyclerView);
        // Setting the layout as linear
        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // Sending reference and data to Adapter
        AdapterMit adapterMit = new AdapterMit(view.getContext(), courseImgMIT, courseNameMIT);
        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(adapterMit);

        return view;
    }
}