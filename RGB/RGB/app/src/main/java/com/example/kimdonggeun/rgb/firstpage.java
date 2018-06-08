package com.example.kimdonggeun.rgb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class firstpage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intropage);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent notifi_service = new Intent(firstpage.this,Notification_Service.class);
                startService(notifi_service);
                Intent intent = new Intent(firstpage.this,MainActivity.class);
                startActivity(intent);

               finish();

            }
        },2000);


    }

}
