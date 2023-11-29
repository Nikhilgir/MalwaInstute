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

public class RgpvFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList courseImgRgpv = new ArrayList<>(Arrays.asList(R.drawable.result,R.drawable.syllabus,R.drawable.notes,R.drawable.timetable,R.drawable.notification, R.drawable.logout));
    ArrayList courseNameRgpv = new ArrayList<>(Arrays.asList("RGPV Results", "Syllabus","Notes","RGPV Time table", "Examinations Notification", "Log out"));



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_rgpv, container, false);



        // Getting reference of recyclerView
        recyclerView =view.findViewById(R.id.recyclerView);
        // Setting the layout as linear
        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // Sending reference and data to Adapter
        AdapterRgpv adapterRgpv = new AdapterRgpv(view.getContext(), courseImgRgpv, courseNameRgpv);
        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(adapterRgpv);
        return view;
    }
}