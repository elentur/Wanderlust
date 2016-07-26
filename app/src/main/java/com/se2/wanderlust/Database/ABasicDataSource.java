package com.se2.wanderlust.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;


/**
 * Created by roberto on 10.07.16.
 */
public class ABasicDataSource {
    protected SQLiteDatabase database;
    protected WanderlustDbHelper dbHelper;

    public ABasicDataSource(Context context) {
        dbHelper = new WanderlustDbHelper(context);
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
