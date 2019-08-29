package com.dfrobot.angelo.blunobasicdemo;

import android.os.Bundle;

public class Add_Paitent extends  BlunoLibrary {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paitent_details);
        onCreateProcess();


    }


    @Override
    public void onConectionStateChange(connectionStateEnum theconnectionStateEnum) {

    }

    @Override
    public void onSerialReceived(String theString) {

    }
}
