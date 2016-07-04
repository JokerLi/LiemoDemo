package com.ijinshan.liemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Li Guoqing on 2016/5/25.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 0;
    private static final String DATABASE_NAME = "liemo.db";
    private static final String TABLE_NAME = "TEST_TABLE";
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            DatabaseColumns._ID + " INTEGER PRIMARY KEY, " +
            DatabaseColumns.NAME + " TEXT, " +
            DatabaseColumns.AGE + " INTEGER" +
            ")";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //upgrade database version callback
    }
}
