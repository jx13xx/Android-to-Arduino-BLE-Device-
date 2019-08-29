package com.dfrobot.angelo.blunobasicdemo;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

public class MyAlarm extends BroadcastReceiver  {
    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 001;


    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();


       // displayNotification();


    }
    /*
    BlunoLibrary func = new BlunoLibrary() {

        @Override
        public void onConectionStateChange(connectionStateEnum theconnectionStateEnum) {

        }

        @Override
        public void onSerialReceived(String theString) {

        }
    };

    */


    /*

    public void displayNotification(){
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.alarm_notification)
                .setContentTitle("Reminder")
                .setContentText("Notification Reminder: Dont forget your pill!");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notificationBuilder());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default");

        builder.setSmallIcon(R.drawable.alarm_notification);
        builder.setContentTitle("Reminder");
        builder.setContentText("Notification Reminder: Dont forget your pill!");

    }

    */
}
