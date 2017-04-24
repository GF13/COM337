package com.example.rachm.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;


/**
 * Created by rachm on 24/04/2017.
 */

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME="dogadoption.db";
    public static final String TABLE_NAME ="dogs_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3 = "BREED";
    public static final String COL_4="AGE";
    public static final String COL_5="SHELTER_NAME";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT, BREED TEXT, AGE INTEGER SHELTER_NAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }
    public boolean insertData(String name, String breed, String age, String shelter_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,name);
        cv.put(COL_3,breed);
        cv.put(COL_4,age);
        cv.put(COL_5,shelter_name);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == 1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
    return res;
    }
    public boolean updateData(String id, String name, String breed, String age, String shelter_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(COL_1,id);
        cv1.put(COL_2,name);
        cv1.put(COL_3,breed);
        cv1.put(COL_4,age);
        cv1.put(COL_5,shelter_name);
        db.update(TABLE_NAME,cv1,"ID = ?",new String[]{id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }
}
