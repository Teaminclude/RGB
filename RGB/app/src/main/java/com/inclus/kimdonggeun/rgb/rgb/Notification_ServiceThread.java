package com.inclus.kimdonggeun.rgb.rgb;


import android.os.Handler;
import android.util.Log;

public class Notification_ServiceThread extends Thread {
    Handler handler;
    boolean isRun = true;
    int time =60; //1분

    public Notification_ServiceThread(Handler handler) {
        this.handler = handler;
    }

    public void stopForever(){
        synchronized (this){
            this.isRun=false;
        }
    }

    public void run(){
        while(isRun){
            handler.sendEmptyMessage(0);
            try{
                Thread.sleep(1000*time);
            }catch(Exception e){
                Log.e("서비스 쓰레드 핸들러","오류",e);
            }

        }

    }
}
