package com.ramdani.crudmobileapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME =
            "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
// TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
        String sql = "create table biodata(no integer primary key, nama text null, nohp text null, jk text null, alamat text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO biodata (no, nama, nohp, jk, alamat) VALUES ('1', 'Rosa', '0812200422770', 'P','Bandung');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
// TODO Auto-generated method stub
        String query;
        query = "DROP TABLE IF EXISTS biodata";
        db.execSQL(query);
        onCreate(db);
    }
}