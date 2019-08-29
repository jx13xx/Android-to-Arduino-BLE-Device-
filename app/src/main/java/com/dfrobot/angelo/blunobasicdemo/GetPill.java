package com.dfrobot.angelo.blunobasicdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GetPill extends BlunoLibrary {

	public static DatabaseHelper myDb;
	public Button buttonScan;
	public Button buttonSerialSend;
	public EditText serialSendText;
	public Button  add_medicine;
	public TextView serialReceivedText;
	public EditText texxt;
	public Button mylogin;
	public Button admin;

	String received_value = "Panadol";

	private ListView pList;
	private List<Medicine_list> plists;
	private GetPill_Adapter nameAdapter2;
	private String getValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view4);
        onCreateProcess();														//onCreate Process by BlunoLibrary
		myDb = new DatabaseHelper(this);

        serialBegin(115200);//set the Uart Baudrate on BLE chip to 115200

		pList = (ListView) findViewById(R.id.pList);
		plists = new ArrayList<>();


		texxt = (EditText) findViewById(R.id.textt);
		//mylogin = (Button)findViewById(R.id.mylogin);
		//admin =   (Button)findViewById(R.id.admin);
        serialReceivedText=(TextView) findViewById(R.id.serialReveicedText);	//initial the EditText of the received data


       // serialSendText=(EditText) findViewById(R.id.serialSendText);			//initial the EditText of the sending data
		add_medicine = (Button) findViewById(R.id.add_medicine);
		buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		//initial the button for sending the data



		Cursor cursor = myDb.getPillcount(received_value);
		if(cursor.moveToFirst()) {
			do{
				Medicine_list value = new Medicine_list(
						cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)),
						cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)),
						cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_4))
				);
				plists.add(value);
				getValue = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3));
			}while (cursor.moveToNext());
		}

		nameAdapter2 = new GetPill_Adapter(this,R.layout.get_pill,plists);
		pList.setAdapter(nameAdapter2);


		//texxt.setText(getValue);
		int num1;
		num1 = Integer.parseInt(getValue);
		int minus_piil;
		minus_piil = num1 -1;
		String str2;
		str2 = Integer.toString(minus_piil);
		//texxt.setText(str2);

		String text12;
		text12 = serialReceivedText.getText().toString();
		texxt.setText("");
		texxt.setText(text12);



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
		texxt.setText("");
		texxt.setText(theString);
		String newtext = texxt.getText().toString();
		((ScrollView)serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);
		if(newtext.contentEquals("x")){
			checkfunc();
		}

	}

	public void checkfunc(){

		int num1;
		num1 = Integer.parseInt(getValue);
		int minus_piil;
		minus_piil = num1 -1;
		String str2;
		str2 = Integer.toString(minus_piil);
		texxt.setText(str2);

		myDb.update_PillCount(str2,received_value);


		Toast.makeText(this," Pill Taken Sucessfully", Toast.LENGTH_SHORT).show();
	}

}