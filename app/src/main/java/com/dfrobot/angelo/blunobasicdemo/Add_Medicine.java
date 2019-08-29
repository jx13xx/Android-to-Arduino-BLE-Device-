package com.dfrobot.angelo.blunobasicdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Medicine extends Activity {
    public static DatabaseHelper myDb;
    public EditText med_name;
    public EditText med_qty;
    public EditText med_date;
    public Button   med_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_add);
        myDb = new DatabaseHelper(this);

        med_name = (EditText)findViewById(R.id.med_name);
        med_qty =  (EditText)findViewById(R.id.med_qty);
        med_date = (EditText)findViewById(R.id.med_date);
        med_btn = (Button)findViewById(R.id.med_btn);

        med_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_Medicine();
            }
        });
    }

    public void add_Medicine(){
        String med1_name, med1_qty,med1_date;
        med1_name = med_name.getText().toString();
        med1_qty = med_qty.getText().toString();
        med1_date = med_date.getText().toString();
        myDb.insert_MedicineDetails(med1_name,med1_qty,med1_date);
        Toast.makeText(this,"Medicine Details: Added", Toast.LENGTH_SHORT).show();
        med_name.setText("");
        med_qty.setText("");
        med_date.setText("");
    }


}