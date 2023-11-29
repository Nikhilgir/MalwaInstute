package com.example.malwainstute.charts;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageButton;

import java.net.*;
import java.io.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.malwainstute.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class Chat extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText messageEditText;
    ImageButton sendButton;
    List<Message> messageList;
    MessageAdapter messageAdapter;

    Socket socket=null;
    BufferedReader br;
    PrintWriter out;

    public static final MediaType JSONs
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        Client k=new Client();
        new Thread(k).start();


        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#771717"));
        actionBar.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        messageList=new ArrayList<>();

        recyclerView =findViewById(R.id.recycler_view);

        messageEditText=findViewById(R.id.message_edit_text);
        sendButton=findViewById(R.id.send_btn);

        //setup recycler view
        messageAdapter=new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        sendButton.setOnClickListener((v)->{
            String question=messageEditText.getText().toString().trim();

            addToChat(question,Message.SENT_BY_ME);
            messageEditText.setText("");
            new Handler().postDelayed((Runnable) () -> {
                if(!question.isEmpty()){
                    k.startWriting(question);
            }
            },2000);
        });
    }
    @SuppressLint("NotifyDataSetChanged")
    void addToChat(String message, String sentBy){
        runOnUiThread(() -> {
            messageList.add(new Message(message,sentBy));
            messageAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
        });
    }

    void addResponse(String response){
        addToChat(response,Message.SENT_BY_BOT);
    }

    class Client implements Runnable{

        public Client(){

        }

        @Override
        public void run() {
            try{
                System.out.println("Sending request to server");
                socket=new Socket("192.168.219.112",4444);
                System.out.println("Connection done");

                br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out=new PrintWriter(socket.getOutputStream(),true);

                startReading();
               // startWriting();

            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public void startReading(){

            //thread--read kr ke deta rhega
            Runnable r1 =()->{
                System.out.println("reader started......");

                while(true){
                    try{
                        String msg=br.readLine();

                        if(msg.equals("exit")){
                            System.out.println("Clint terminated thr chat");
                            break;
                        }
                        System.out.println("Server : "+msg);
                        addResponse(msg);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };
            new Thread(r1).start();
        }

        public void startWriting(String mess){

            //thread--data user lega or send krega clint tk
            Runnable r2 =()->{
                System.out.println("writer started...");
                    try{
                        out.println(mess);
                        out.flush();

                    }catch(Exception e){
                        e.printStackTrace();
                    }

            };
            new Thread(r2).start();

        }
    }
}