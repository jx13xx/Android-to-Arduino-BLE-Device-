package com.dfrobot.angelo.blunobasicdemo;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Adminlogin extends BlunoLibrary {
    public static DatabaseHelper myDb;
    private TextView user_text;
    private Button user;
    private TextView lid_stat;
    private Button open_btn;
    private Button close_btn;
    private Button buttonScan;
    private Button buttonSerialSend;
    private EditText serialSendText;
    private TextView serialReceivedText;
    private String receivedtext;
    private EditText test1;
    private EditText medicine_search;
    private TextView count;
    private Button  btn_search;
    public static String med_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        onCreateProcess();														//onCreate Process by BlunoLibrary
        myDb = new DatabaseHelper(this);

        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200
        //count_value = 50;
        user_text = (TextView)findViewById(R.id.user_text);
        user = (Button)findViewById(R.id.user);
        lid_stat = (TextView)findViewById(R.id.lid_stat);
        open_btn = (Button)findViewById(R.id.open_btn);
        close_btn = (Button)findViewById(R.id.close_btn);
        serialReceivedText=(TextView) findViewById(R.id.serialReveicedText);	//initial the EditText of the received data
        serialSendText=(EditText) findViewById(R.id.serialSendText);			//initial the EditText of the sending data
        test1 = (EditText) findViewById(R.id.test1);
        count = (TextView) findViewById(R.id.count);
        medicine_search = (EditText)findViewById(R.id.search);
        btn_search = (Button)findViewById(R.id.btn_search);

        getMedicineCount();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMedicine();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serialSendText.setText("B");
                serialSend(serialSendText.getText().toString());
                user_text.setText("User Verified");

            }
        });
        open_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serialSendText.setText("U");
                serialSend(serialSendText.getText().toString());
                final String show;

                //lid_stat.setText("Pill Box: Closed");
               // serialReceivedText.setText(null);
                receivedtext = serialReceivedText.getText().toString();
                test1.setText(receivedtext);
                test1.getText().clear();
                test1.setText("LED ON");
                show = test1.getText().toString();
                if(!show.isEmpty()){
                    if(receivedtext.contentEquals("LED ON")){
                        test1.setText("Working1");
                        //int update_count = Integer.parseInt(count_value);
                       // update_count = update_count - 1;
                        //count.setText(update_count);
                       // count_value = count_value -1;
                      //  count.setText(count_value);
                    }
                }



               // receivedtext = serialReceivedText.getText().toString();
               // receivedtext=  serialReceivedText.getText().toString();
                lid_stat.setText(receivedtext);


            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serialSendText.setText("D");
                serialSend(serialSendText.getText().toString());
                lid_stat.setText("Pill Box: Open");
                receivedtext = serialReceivedText.getText().toString();
                final String show;
                test1.setText(receivedtext);
                test1.getText().clear();
                test1.setText("LED OFF");
                show = test1.getText().toString();
                if(!show.isEmpty()){
                    if(receivedtext.contentEquals("LED OFF")){
                        test1.setText("Working2");
                    }
                }


            }
        });
        buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		//initial the button for sending the data
        buttonSerialSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                serialSend(serialSendText.getText().toString());				//send the data to the BLUNO
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

    public void searchMedicine(){
        med_search = medicine_search.getText().toString();
        final String medval;

        Intent intent = new Intent(this, MedicineSearch.class);
        intent.putExtra("EXTRA_MESSAGE",med_search);
        startActivity(intent);

    }


    public  void getMedicineCount(){

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
    public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
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
