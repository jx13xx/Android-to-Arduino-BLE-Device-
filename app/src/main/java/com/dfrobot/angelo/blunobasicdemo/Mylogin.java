package com.dfrobot.angelo.blunobasicdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Mylogin extends BlunoLibrary {

    public Button add_pat;
    public Button add_med;
    public Button reminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        //onCreateProcess();                                                        //onCreate Process by BlunoLibrary

        add_pat = (Button)findViewById(R.id.add_pat);
        add_med = (Button)findViewById(R.id.add_med);
        reminder = (Button)findViewById(R.id.reminder);

        add_pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_paitents();
            }
        });

        add_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_medicine();
            }
        });
    }

    public void add_medicine(){
        Intent intent = new Intent(this, MedicineDetails.class);
        startActivity(intent);

    }

    public void add_paitents(){
        Intent intent = new Intent(this, Add_Paitent.class);
        startActivity(intent);
    }
    @Override
    public void onConectionStateChange(connectionStateEnum theconnectionStateEnum) {

    }

    @Override
    public void onSerialReceived(String theString) {

    }
}
