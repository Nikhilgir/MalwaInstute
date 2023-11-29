package com.example.malwainstute.lists;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.malwainstute.charts.Chat;
import com.example.malwainstute.R;
import com.example.malwainstute.login.Mit_Login;
import com.example.malwainstute.utils.MitWebsites;
import com.example.malwainstute.utils.MitWebsites;
import com.example.malwainstute.websites.Websites1;

import java.util.ArrayList;

public class AdapterMit extends RecyclerView.Adapter<AdapterMit.ViewHolder> {

    static String value="";
    public static void setValue(String value) {
        AdapterMit.value = value;
    }
    public static String getValue() {
        return value;
    }

    ArrayList courseImgMIT, courseNameMIT;
    Context context;


    // Constructor for initialization
    public AdapterMit(Context context, ArrayList courseImg, ArrayList courseName) {
        this.context = context;
        this.courseImgMIT = courseImg;
        this.courseNameMIT = courseName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        // Passing view to ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TypeCast Object to int type
        int res = (int) courseImgMIT.get(position);
        holder.images.setImageResource(res);
        holder.text.setText((String) courseNameMIT.get(position));
        if (position==0){
            int greenColorValue = Color.parseColor("#b2dfcf");
            holder.itemView.setBackgroundTintList(ColorStateList.valueOf(greenColorValue));

        }


        holder.text.setOnClickListener(v -> {
            if (position==0) {
                Intent items = new Intent(context, Chat.class);
                context.startActivity(items);
            }
            if (position==1) {
                value= MitWebsites.STUDENT_LOGIN;
                Intent items = new Intent(context, Websites1.class);
                context.startActivity(items);
            }
            if (position==2) {
                value=MitWebsites.STAFF_LOGIN;
                Intent items = new Intent(context, Websites1.class);
                context.startActivity(items);
            }
            if (position==3){
                SharedPreferences sharedPreferences= context.getSharedPreferences(Mit_Login.PREFS_NAME,0);
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("hasLoggedIn",false);
                editor.apply();
                Intent iHome=new Intent(context, Mit_Login.class);
                context.startActivity(iHome);
            }
        });

    }

    @Override
    public int getItemCount() {
        // Returns number of items
        // currently available in Adapter
        return courseImgMIT.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView text;


        public ViewHolder(View view) {
            super(view);
            images = (ImageView) view.findViewById(R.id.courseImg);
            text = (TextView) view.findViewById(R.id.courseName);
        }
    }
}
