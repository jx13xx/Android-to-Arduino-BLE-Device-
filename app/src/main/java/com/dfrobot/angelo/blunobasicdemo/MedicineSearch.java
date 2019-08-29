package com.dfrobot.angelo.blunobasicdemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MedicineSearch extends Activity {

    public static DatabaseHelper myDb;

    private ListView pList;
    private List<Medicine_list> plists;
    private MedineSearch_Adapter nameAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view4);
        myDb = new DatabaseHelper(this);
        Intent intent = getIntent();
        String received_value;

        received_value = intent.getStringExtra("EXTRA_MESSAGE");

        pList = (ListView) findViewById(R.id.pList);
        plists = new ArrayList<>();

        Cursor cursor = myDb.getMedicinedata(received_value);
        if(cursor.moveToFirst()) {
            do{
                Medicine_list value = new Medicine_list(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_4))
                );
                plists.add(value);
            }while (cursor.moveToNext());
        }

        nameAdapter2 = new MedineSearch_Adapter(this,R.layout.medicine_viewdetails,plists);
        pList.setAdapter(nameAdapter2);


    }



}
