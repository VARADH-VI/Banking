package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(7349495726,'Varadh',9477610.00,'vrvaradh2@gmail.com','XXXXXXXXXXXX1234','ABC09845543')");
        db.execSQL("insert into user_table values(9895652152,'Rohnda',0.00,'rhonda@gmail.com','XXXXXXXXXXXX5678','BCA98765458')");
        db.execSQL("insert into user_table values(9916687061,'Venkatesh',130590.56,'venkatesh@gmail.com','XXXXXXXXXXXX6789','CAB88954321')");
        db.execSQL("insert into user_table values(7349846152,'Rithvik',8881.17,'rithvik@gmail.com','XXXXXXXXXXXX2345','ABC76543690')");
        db.execSQL("insert into user_table values(9738301189,'Rupali',260300.48,'rupali@gmail.com','XXXXXXXXXXXX8794','BCA65432189')");
        db.execSQL("insert into user_table values(9856136665,'Shivani',0.00,'shivani@gmail.com','XXXXXXXXXXXX3452','CAB54321050')");
        db.execSQL("insert into user_table values(7946413561,'Rama',0.00,'rama@gmail.com','XXXXXXXXXXXX5286','ABC43210965')");
        db.execSQL("insert into user_table values(9966121313,'Frenchesca',0.00,'frenchesca@gmail.com','XXXXXXXXXXXX5234','BCA32109855')");
        db.execSQL("insert into user_table values(7874561335,'Priya',0.00,'priya@gmail.com','XXXXXXXXXXXX5467','CAB21098788')");
        db.execSQL("insert into user_table values(8541653216,'Liya',0.00,'liya@gmail.com','XXXXXXXXXXXX4530','ABC10987610')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
