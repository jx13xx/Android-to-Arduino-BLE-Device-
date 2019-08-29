package com.dfrobot.angelo.blunobasicdemo;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity  extends BlunoLibrary {

    public static DatabaseHelper myDb;
	public Button buttonScan;
	public Button buttonSerialSend;
	public EditText serialSendText;
	public Button  add_medicine;
	public TextView serialReceivedText;
	public Button mylogin;
	public Button admin;
	public Button get_pill;
	public Button enroll_finger;
	public Button abt_us;
	public TextView pill_status;
	public Button refill_medicine;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        onCreateProcess();														//onCreate Process by BlunoLibrary


        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200
        myDb = new DatabaseHelper(this);
		mylogin = (Button)findViewById(R.id.mylogin);
		admin =   (Button)findViewById(R.id.admin);
        serialReceivedText=(TextView) findViewById(R.id.serialReveicedText);	//initial the EditText of the received data
        serialSendText=(EditText) findViewById(R.id.serialSendText);			//initial the EditText of the sending data
		add_medicine = (Button) findViewById(R.id.add_medicine);
		abt_us = (Button) findViewById(R.id.abt_us);
		pill_status = (TextView)findViewById(R.id.pill_status);
		refill_medicine = (Button)findViewById(R.id.refill_pill);
		refill_medicine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                refill_medicineDatabase();
            }
        });
		abt_us.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				openAboutUs();
			}
		});


		get_pill = (Button)findViewById(R.id.get_pill);
		get_pill.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				changing_pill();
			}
		});

		enroll_finger = (Button)findViewById(R.id.enroll_finger);
		enroll_finger.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				fingerEnrollment();
			}
		});

		add_medicine.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				openAddMedicine();
			}
		});

        buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		//initial the button for sending the data
        buttonSerialSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				serialSend(serialSendText.getText().toString());				//send the data to the BLUNO
			}
		});


        buttonScan = (Button) findViewById(R.id.buttonScan);					//initial the button for scanning the BLE device

		buttonScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				buttonScanOnClickProcess();										//Alert Dialog for selecting the BLE device
			}
		});

        mylogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				openLoginActivity();
			}
		});


		admin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				openAdminpage();
			}
		});



    checkPillLow();
    handler.postDelayed(runnable,60000);


	}

	public void refill_medicineDatabase(){
		Intent intent = new Intent(this,RefillPill.class);
		startActivity(intent);
	}

    private Handler handler = new Handler();
	private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            checkPillLow();
            handler.postDelayed(this,60000);
        }

    };


	public void checkPillLow(){
		String medicine_name = "";
	   if(myDb.pillLow(medicine_name) == true){
          // Toast.makeText(this," Pill Count is Low", Toast.LENGTH_SHORT).show();
           NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                   .setDefaults(NotificationCompat.DEFAULT_ALL)
                   .setSmallIcon(R.drawable.alarm_notification)
                   .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.alarm_notification))
                   .setContentTitle("Notification From Remind Me")
                   .setContentText("Your " +medicine_name+ " pill is low. Please Refill Compartment");

           NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
           notificationManager.notify(1,notificationBuilder.build());
           pill_status.setText("Pill Count: Low");

       }
       else {

       }
    }

	public void openAboutUs(){
		Intent intent = new Intent(this,Aboutus.class);
		startActivity(intent);

	}

	public void fingerEnrollment(){
		Intent intent = new Intent(this,FingerEnrollmentPage.class);
		startActivity(intent);
	}

	public void changing_pill(){
		Intent intent = new Intent(this,GetPill.class);
		startActivity(intent);
	}

	public  void openAddMedicine(){
		Intent intent = new Intent(this, Add_Medicine.class);
		startActivity(intent);
	}
	public void openAdminpage(){
		Intent intent = new Intent(this, AdminLock.class);
		startActivity(intent);

	}

	public void openLoginActivity(){

		Intent intent = new Intent(this, MedicineDetails.class);
		startActivity(intent);
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