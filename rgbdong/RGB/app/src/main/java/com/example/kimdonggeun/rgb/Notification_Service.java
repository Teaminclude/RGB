package com.example.kimdonggeun.rgb;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;

public class Notification_Service extends Service {
    NotificationManager Notifi_manager;
    Notification_ServiceThread thread;
    Notification Notifi;
    NotificationChannel mChannel;


    String channel_id = "notifi";
    String channel_name = "notifi_name";
    int importance = NotificationManager.IMPORTANCE_LOW;

    int hour = 21;
    int minute = 0;

    public Notification_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notifi_manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myServiceHandler handler1 = new myServiceHandler();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(channel_id,channel_name,importance);
            mChannel.enableLights(true);
            Notifi_manager.createNotificationChannel(mChannel);
        }

        thread = new Notification_ServiceThread(handler1);
        thread.start();



        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class myServiceHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            //날짜 아니면 리턴
            Calendar now = Calendar.getInstance();

           // if((now.get(Calendar.HOUR_OF_DAY))!=hour||now.get(Calendar.MINUTE)!=minute){
            //    return;
          //  }


            Intent intent = new Intent(Notification_Service.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(Notification_Service.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                Notifi = new Notification.Builder(getApplicationContext())
                        .setContentTitle("RGB")
                        .setContentText("오늘 하루의 컬러를 정하지 않았다면 정해보시지 않겠어요?")
                        .setChannelId(channel_id)
                        .setSmallIcon(R.drawable.data)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build();
            }else{
                Notifi = new Notification.Builder(getApplicationContext())
                        .setContentTitle("RGB")
                        .setContentText("오늘 하루의 컬러를 정하지 않았다면 정해보시지 않겠어요?")
                        .setSmallIcon(R.drawable.data)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build();
            }


            //소리추가
            Notifi.defaults = Notification.DEFAULT_SOUND;

            //알림 소리를 한번만 내도록
            Notifi.flags = Notification.FLAG_ONLY_ALERT_ONCE;

            //확인하면 자동으로 알림이 제거 되도록
            Notifi.flags = Notification.FLAG_AUTO_CANCEL;

            Notifi_manager.notify( 1 , Notifi);

            //토스트 띄우기
            Toast.makeText(Notification_Service.this, "쓰레드 돌아가고있음", Toast.LENGTH_LONG).show();
        }
    };


}
