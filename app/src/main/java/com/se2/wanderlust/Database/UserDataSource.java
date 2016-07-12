package com.se2.wanderlust.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by
 * Roberto on 10.07.16.
 */
public class UserDataSource extends ABasicDataSource {

    /**
     * summary of all user table columns
     */
    private String[] allColumns = {
        WanderlustDbHelper.USER_COLUMN_ID,
        WanderlustDbHelper.USER_COLUMN_EMAIL,
        WanderlustDbHelper.USER_COLUMN_PASSWORD,
        WanderlustDbHelper.USER_COLUMN_PUBLIC_PHOTO,
        WanderlustDbHelper.USER_COLUMN_HPA,
        WanderlustDbHelper.USER_COLUMN_TRAKING_RATE
    };

    /**
     * Creates a UserDataSource Object for interaction with the database.
     * @param context form activity
     */
    public UserDataSource(Context context) {
        super(context);
    }

    /**
     * Insert a User in the database and gives back a user object. Throws Exception if user already exist.
     * @param email of the user
     * @param password of the user
     * @return User object
     */
    public User insertUser(String email, String password) {

        ContentValues values = new ContentValues();

        values.put(WanderlustDbHelper.USER_COLUMN_EMAIL, email);
        values.put(WanderlustDbHelper.USER_COLUMN_PASSWORD, password);

        long insertId = database.insert(WanderlustDbHelper.TABLE_USER, null, values);

        Cursor cursor = database.query(
                WanderlustDbHelper.TABLE_USER, 
                allColumns, 
                WanderlustDbHelper.USER_COLUMN_ID + " =?",
                new String[] {String.valueOf(insertId)},
                null, 
                null, 
                null
        );

        cursor.moveToFirst();

        User newUser = cursorToUser(cursor);

        cursor.close();

        return newUser;

    }

    /**
     * Returns a List of all User in the database.
     * @return user object list
     */
    public ArrayList<User> getAllUser(){
        ArrayList<User> Users = new ArrayList<User>();

        Cursor cursor = database.query(WanderlustDbHelper.TABLE_USER, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User User = cursorToUser(cursor);
            Users.add(User);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Users;
    }

    /**
     * Retruns the first user how fits with the given E-Mail and Password
     * @param email  of the user
     * @param password  of the user
     * @return user object
     */
    public User getUser(String email, String password){

        Cursor cursor = database.query(
                WanderlustDbHelper.TABLE_USER,
                allColumns,
                WanderlustDbHelper.USER_COLUMN_EMAIL + " =? AND " +  WanderlustDbHelper.USER_COLUMN_PASSWORD + " =?",
                new String[] {email,password},
                null,
                null,
                null
        );

        cursor.moveToFirst();
        User User = cursorToUser(cursor);
        cursor.close();
        return User;
    }

    /**
     * Updates the user in the database
     * @param user to update
     * @return int primary key of the sql table
     */
    public int updateUser(User user){

        ContentValues values = new ContentValues();

        values.put(WanderlustDbHelper.USER_COLUMN_EMAIL, user.getEmail());
        values.put(WanderlustDbHelper.USER_COLUMN_PASSWORD, user.getPassword());
        values.put(WanderlustDbHelper.USER_COLUMN_PUBLIC_PHOTO, user.getPassword());
        values.put(WanderlustDbHelper.USER_COLUMN_HPA, user.getHpa());
        values.put(WanderlustDbHelper.USER_COLUMN_TRAKING_RATE, user.getTracking_rate());

        return database.update(WanderlustDbHelper.TABLE_USER, values, WanderlustDbHelper.USER_COLUMN_ID + " = " + user.getId(),null );
    }

    /**
     * Converts the cursor to a user object.Returns null if not exist.
     * @param cursor from database
     * @return user obbject
     */
    private User cursorToUser(Cursor cursor) {
        User user = null;

        if(cursor.getCount() > 0) {
            user = new User();
            user.setId(cursor.getLong(0));
            user.setEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
        }

        return user;
    }

    /**
     * Counts all column of the table user
     * @return number of column
     */
    private int getNumberOfEntries() {
        String countQuery = "SELECT  * FROM " + WanderlustDbHelper.TABLE_USER;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}
