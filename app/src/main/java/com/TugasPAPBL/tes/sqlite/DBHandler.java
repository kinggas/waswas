package com.TugasPAPBL.tes.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "dbuser";
    public static final int DB_VERSION = 1;
    //String query tabel konsumsi
    String CREATE_TABLE_KONSUMSI = "create table " + User.KonsumsiUser.TABLE_NAME +
            " (" + User.KonsumsiUser.COL_FOOD_ID + " LONG PRIMARY KEY)";
    private ContentValues values;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // TODO Auto-generated constructor stub
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + User.UserDetails.TABLE_NAME + " ( " +
                User.UserDetails.COL_ID + " INTEGER PRIMARY KEY," +
                User.UserDetails.COL_NAME + " VARCHAR(50)," +
                User.UserDetails.COL_JK + " VARCHAR(50)," +
                User.UserDetails.COL_TGL + " VARCHAR(10)," +
                User.UserDetails.COL_BB + " INTEGER," +
                User.UserDetails.COL_TB + " INTEGER," +
                User.UserDetails.COL_LVL + " VARCHAR(50))";
        Log.d("Data", "onCreate: " + query);
        db.execSQL(query);
        db.execSQL(CREATE_TABLE_KONSUMSI);
    }

    //insert
    public boolean addUser(String nama, String jk, String tgl, int bb, int tb, String lvlakt) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO biodata VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        values = new ContentValues();
        values.put(User.UserDetails.COL_NAME, nama);
        values.put(User.UserDetails.COL_JK, jk);
        values.put(User.UserDetails.COL_TGL, tgl);
        values.put(User.UserDetails.COL_BB, bb);
        values.put(User.UserDetails.COL_TB, tb);
        values.put(User.UserDetails.COL_LVL, lvlakt);

        long sid = db.insert(User.UserDetails.TABLE_NAME, null, values);

        return sid > 0;
    }

    //metode untuk mengambil data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + User.UserDetails.TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String nama, String jk, String tgl, int bb, int tb, String lvlakt) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + User.UserDetails.TABLE_NAME + " SET nama = '" + nama + "', jk = '" + jk + "', tgl = '" + tgl + "', bb = '" + bb + "', tb = '" + tb + "', lvlakt = '" + lvlakt + "' WHERE id=1";
        db.execSQL(query);
        db.close();
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getNama() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select nama from " + User.UserDetails.TABLE_NAME + " where id=1", null);
        return res;
    }
}
