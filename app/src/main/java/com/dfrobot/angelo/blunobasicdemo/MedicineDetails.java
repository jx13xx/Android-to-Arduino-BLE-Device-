package com.dfrobot.angelo.blunobasicdemo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MedicineDetails extends BlunoLibrary {

   // NotificationCompat.Builder notification;
  //  private static final int uniqueID = 45612;


    TimePicker timePicker;
    Button buttonSetAlarm;
    EditText medicine_name;
    EditText medicine_brand;
    EditText medicine_expiry;
    EditText medicine_qty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_details);

      //  notification = new NotificationCompat.Builder(this);
     //   notification.setAutoCancel(true);

        medicine_name = (EditText) findViewById(R.id.medicine_name);
        medicine_brand = (EditText) findViewById(R.id.medicine_brand);
        medicine_expiry = (EditText)findViewById(R.id.medicine_expiry);
        medicine_qty = (EditText)findViewById(R.id.medicine_qty);


        buttonSetAlarm = (Button)findViewById(R.id.buttonSetAlarm);

        timePicker = (TimePicker)findViewById(R.id.timePicker);

        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute(),
                        0
                );
                setAlarm(calendar.getTimeInMillis());
            }
            
        });

    }

    private void setAlarm(long timeInMillis) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this,MyAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setRepeating(AlarmManager.RTC,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);

        medicine_name.setText("");
        medicine_brand.setText("");
        medicine_expiry.setText("");
        medicine_qty.setText("");
       // Toast.makeText(this," Medicine Added to Database", Toast.LENGTH_SHORT).show();
        Toast.makeText(this," Reminder Added", Toast.LENGTH_SHORT).show();
        /*
        notification.setSmallIcon(R.drawable.alarm_notification);
        notification.setTicker("This is the message");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Title of Notificaton");
        notification.setContentText("Body of the notification");

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        */

    }


    @Override
    public void onConectionStateChange(connectionStateEnum theconnectionStateEnum) {

    }

    @Override
    public void onSerialReceived(String theString) {

    }
}
