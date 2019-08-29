package com.dfrobot.angelo.blunobasicdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RefillPill extends Activity  {
    public static DatabaseHelper myDb;

    public EditText pill_refill_amt;
    public Button refill_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refill_pill);
        myDb = new DatabaseHelper(this);

        pill_refill_amt = (EditText)findViewById(R.id.pill_refill_amt);
        refill_btn = (Button) findViewById(R.id.refill_btn);
        refill_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePillValue();
            }
        });

    }

    public void updatePillValue(){
        String pill_update_value = pill_refill_amt.getText().toString();
        String medicine_name = "Panadol";
        myDb.update_Meds(pill_update_value,medicine_name);
        Toast.makeText(this," Pill has been updated", Toast.LENGTH_SHORT).show();
    }
}
