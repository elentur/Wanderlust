package com.se2.wanderlust.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by
 * Team Wanderlust on 10.07.16.
 * WanderlustDbHelper creates all necessary tables and controls the behavior when Database will be update
 */
public class WanderlustDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    /**
     * TABLE
     */
    public static final String TABLE_USER = "user";

    /**
     * COLUMNS USER
     */
    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_LASTNAME = "lastname";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_PUBLIC_PHOTO = "public_photo";
    public static final String USER_COLUMN_HPA = "hpa";
    public static final String USER_COLUMN_TRAKING_RATE = "tracking_rate";

    // Database creation sql statement
    private static final String SQL_USER_CREATE = "create table " + TABLE_USER
            + "( " + USER_COLUMN_ID + " INTEGER primary key autoincrement,"
            + USER_COLUMN_NAME + " TEXT,"
            + USER_COLUMN_LASTNAME + " TEXT,"
            + USER_COLUMN_EMAIL + " TEXT UNIQUE ON CONFLICT FAIL,"
            + USER_COLUMN_PASSWORD + " TEXT,"
            + USER_COLUMN_PUBLIC_PHOTO + " INTEGER,"
            + USER_COLUMN_HPA + " REAL,"
            + USER_COLUMN_TRAKING_RATE + " INTEGER"
            + ");";

    /**
     * Creates a WanderlustDbHelper object.
     * @param context from Activity
     */
    public WanderlustDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_USER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

}
