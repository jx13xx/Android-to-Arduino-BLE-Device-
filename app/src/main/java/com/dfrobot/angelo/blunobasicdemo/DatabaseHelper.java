package com.dfrobot.angelo.blunobasicdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Medicine.db";
    public static final String TABLE_NAME = "Med_details";
    public static final String COL_1 = "MED_NO";
    public static final String COL_2 = "MED_NAME";
    public static final String COL_3 = "MED_VALUE";
    public static final String COL_4 = "MED_EXPIRY";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (MED_NO INTEGER PRIMARY KEY AUTOINCREMENT,MED_NAME TEXT,MED_VALUE TEXT,MED_EXPIRY TEXT)");

    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    String medicine = "Panadol";

    public boolean insert_MedicineDetails(String medicine_name, String medicine_qty, String medicine_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, medicine_name);
        contentValues.put(COL_3, medicine_qty);
        contentValues.put(COL_4, medicine_date);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getMedicinedata(String med_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Med_details where MED_NAME=?", new String[]{med_name});
        return c;
    }

    public Cursor getPillcount(String medicine) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Med_details where MED_NAME=?", new String[]{medicine});
        return c;
    }

    public boolean update_PillCount(String med_value, String medicine) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update Med_details SET MED_VALUE=? where MED_NAME=?", new String[]{med_value, medicine});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean update_Meds(String med_value, String medicine) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update Med_details SET MED_VALUE=? where MED_NAME=?", new String[]{med_value, medicine});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean pillLow(String medicine) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Med_details WHERE MED_VALUE <6 AND MED_NAME=?", new String[]{medicine});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;


    }

}
