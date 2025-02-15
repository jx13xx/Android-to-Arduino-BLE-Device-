package com.dfrobot.angelo.blunobasicdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class FingerEnrollmentPage extends BlunoLibrary {

    public static DatabaseHelper myDb;


    private Button buttonScan;
    private Button buttonSerialSend;
    private EditText serialSendText;
    private TextView serialReceivedText;
    private Button enrol_switch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerprint);
        onCreateProcess();														//onCreate Process by BlunoLibrary
        myDb = new DatabaseHelper(this);

        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200
        //count_value = 50;

        serialReceivedText=(TextView) findViewById(R.id.serialReveicedText);	//initial the EditText of the received data
        serialSendText=(EditText) findViewById(R.id.serialSendText);			//initial the EditText of the sending data




        buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		//initial the button for sending the data
        buttonSerialSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                serialSend(serialSendText.getText().toString());				//send the data to the BLUNO
            }
        });

        buttonSerialSend.setEnabled(false);

        enrol_switch = (Button)findViewById(R.id.enrol_switch);
        enrol_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serialSendText.setText("U");
                serialSend(serialSendText.getText().toString());
                buttonSerialSend.setEnabled(true);
            }
        });

        buttonScan = (Button) findViewById(R.id.buttonScan);					//initial the button for scanning the BLE device
        buttonScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                buttonScanOnClickProcess();										//Alert Dialog for selecting the BLE device
            }
        });
    }

    protected void onResume(){
        super.onResume();
        System.out.println("BlUNOActivity onResume");
        onResumeProcess();														//onResume Process by BlunoLibrary
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();														//onPause Process by BlunoLibrary
    }

    protected void onStop() {
        super.onStop();
        onStopProcess();														//onStop Process by BlunoLibrary
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

    @Override
    public void onConectionStateChange(BlunoLibrary.connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
        switch (theConnectionState) {											//Four connection state
            case isConnected:
                buttonScan.setText("Connected");
                break;
            case isConnecting:
                buttonScan.setText("Connecting");
                break;
            case isToScan:
                buttonScan.setText("Scan");
                break;
            case isScanning:
                buttonScan.setText("Scanning");
                break;
            case isDisconnecting:
                buttonScan.setText("isDisconnecting");
                break;
            default:
                break;
        }
    }

    @Override
    public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
        // TODO Auto-generated method stub
        serialReceivedText.append(theString);							//append the text into the EditText
        //The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.
        ((ScrollView)serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);
    }




}
