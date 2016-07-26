package com.se2.wanderlust.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;


/**
 * This class represents a instance of the ABasicDataSource object.
 * It opens the necessary project database .
 * Created by
 * Team Wanderlust on 10.07.16.
 */
public class ABasicDataSource {
    protected SQLiteDatabase database;
    protected WanderlustDbHelper dbHelper;

    /**
     * A object of the ABasicDataSource
     * @param context from activity
     */
    public ABasicDataSource(Context context) {
        dbHelper = new WanderlustDbHelper(context);
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * opens a writable database
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * closes the database
     */
    public void close() {
        dbHelper.close();
    }
}
