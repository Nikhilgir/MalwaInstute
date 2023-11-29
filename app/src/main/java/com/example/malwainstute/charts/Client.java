package com.example.malwainstute.charts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{
    Socket socket;

    BufferedReader br;
    PrintWriter out;

    Chat c=new Chat();
    public Client(){

    }

    @Override
    public void run() {
        try{
            System.out.println("Sending request to server");
            socket=new Socket("192.168.33.112",4444);
            System.out.println("Connection done");

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(),true);

            startReading();
            //startWriting();

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
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        };
        new Thread(r1).start();

    }

//    public void startWriting(){
//
//        //thread--data user lega or send krega clint tk
//        Runnable r2 =()->{
//            System.out.println("writer started...");
//            while(true){
//                try{
//                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
//                    String content=br1.readLine();
//                    out.println(content);
//                    out.flush();
//
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//
//        };
//        new Thread(r2).start();
//
//    }
}
