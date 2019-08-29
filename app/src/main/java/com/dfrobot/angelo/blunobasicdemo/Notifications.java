package com.dfrobot.angelo.blunobasicdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class Notifications extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


       // notification_call();
    }


    public void notification_call(){
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.alarm_notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.alarm_notification))
                .setContentTitle("Notification From Remind Me")
                .setContentText("Your pill is low. Please Refill Compartment");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notificationBuilder.build());
    }

}
