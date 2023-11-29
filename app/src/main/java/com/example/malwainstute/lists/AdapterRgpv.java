package com.example.malwainstute.lists;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.malwainstute.R;
import com.example.malwainstute.charts.Chat;
import com.example.malwainstute.login.Mit_Login;
import com.example.malwainstute.utils.RgpvWebsites;
import com.example.malwainstute.websites.Websites1;

import java.util.ArrayList;

public class AdapterRgpv extends RecyclerView.Adapter<AdapterRgpv.ViewHolder> {

    static String value="";
    public static void setValue(String value) {
        AdapterRgpv.value = value;
    }
    public static String getValue() {
        return value;
    }

    ArrayList courseImgRgpv, courseNameRgpv;
    Context context;


    // Constructor for initialization
    public AdapterRgpv(Context context, ArrayList courseImg, ArrayList courseName) {
        this.context = context;
        this.courseImgRgpv = courseImg;
        this.courseNameRgpv = courseName;
    }

    @NonNull
    @Override
    public AdapterRgpv.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        // Passing view to ViewHolder
        AdapterRgpv.ViewHolder viewHolder = new AdapterRgpv.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRgpv.ViewHolder holder, int position) {
        // TypeCast Object to int type
        int res = (int) courseImgRgpv.get(position);
        holder.images.setImageResource(res);
        holder.text.setText((String) courseNameRgpv.get(position));


        holder.text.setOnClickListener(v -> {
            if (position==0) {
                value= RgpvWebsites.RGPV_RESULT;
                Intent items = new Intent(context, Websites1.class);
                context.startActivity(items);
            }
            if (position==1) {
                value=RgpvWebsites.RGPV_SYLLABUS;
                Intent items = new Intent(context, Websites1.class);
                context.startActivity(items);
            }
            if (position==2) {
                value=RgpvWebsites.RGPV_NOTES;
                Intent items = new Intent(context, Websites1.class);
                context.startActivity(items);
            }
            if (position==3) {
                value=RgpvWebsites.RGPV_TIME_TABLE;
                Intent items = new Intent(context, Websites1.class);
                context.startActivity(items);
            }
            if (position==4) {
                value=RgpvWebsites.RGPV_EXAM_NOTIFICATION;
                Intent items = new Intent(context, Websites1.class);
                context.startActivity(items);
            }
            if (position==5){
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
        return courseImgRgpv.size();
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
