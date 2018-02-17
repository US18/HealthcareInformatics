package com.example.uplabdhisingh.t95.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by uplabdhisingh on 17/02/18.
 */

public class HealthCareDbHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="healthcare.db";
    public static final int DATABASE_VERSION=1;

    public HealthCareDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE=
                "CREATE TABLE "+
                HealthCareContract.HealthCareEntry.TABLE_NAME +
                        " (" +
                HealthCareContract.HealthCareEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HealthCareContract.HealthCareEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                HealthCareContract.HealthCareEntry.COLUMN_TIME + " TEXT NOT NULL, " +
                HealthCareContract.HealthCareEntry.COLUMN_AADHAR + " TEXT NOT NULL, " +
                HealthCareContract.HealthCareEntry.COLUMN_PINCODE + " TEXT NOT NULL, " +
                HealthCareContract.HealthCareEntry.COLUMN_CATEGORIES + " TEXT NOT NULL, " +
                HealthCareContract.HealthCareEntry.COLUMN_FOOD + " TEXT NOT NULL, " +
                HealthCareContract.HealthCareEntry.COLUMN_CLOTHES + " TEXT NOT NULL, " +
                HealthCareContract.HealthCareEntry.COLUMN_VACCINE + " TEXT NOT NULL, " +
                HealthCareContract.HealthCareEntry.COLUMN_OTHER + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + HealthCareContract.HealthCareEntry.TABLE_NAME);
        onCreate(db);
    }
}
