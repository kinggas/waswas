package com.TugasPAPBL.tes.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class KonsumsiHelper {
    private DBHandler dataBaseHelper;
    private SQLiteDatabase database;
    private Context context;

    public KonsumsiHelper(Context context) {
        this.context = context;
    }

    public KonsumsiHelper open() throws SQLException {
        dataBaseHelper = new DBHandler(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<Long> getAllFoodID() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + User.KonsumsiUser.TABLE_NAME, null);
        cursor.moveToFirst();
        ArrayList<Long> konsumsi = new ArrayList<>();
        if (cursor.getCount() > 0) {
            do {
                konsumsi.add(cursor.getLong(cursor.getColumnIndexOrThrow(User.KonsumsiUser.COL_FOOD_ID)));
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return konsumsi;
    }

    public long insertFoodID(Long foodID) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(User.KonsumsiUser.COL_FOOD_ID, foodID);
        return database.insert(User.KonsumsiUser.TABLE_NAME, null, initialValues);
    }

    public int delete(Long id) {
        return database.delete(User.KonsumsiUser.TABLE_NAME, User.KonsumsiUser.COL_FOOD_ID + " = '" + id + "'", null);
    }

    public void deletePerHari(Long id) {
        String sql = "DELETE FROM " + User.KonsumsiUser.TABLE_NAME + " WHERE datetime(" + String.valueOf(Calendar.getInstance().getTimeInMillis()) + "/10000000 - 62135596800, 'unixepoch') <= datetime('now', '-1 day')";
        database.execSQL(sql);
    }
}
