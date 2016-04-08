package com.swchung.softchain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by icete on 2016-04-06.
 */
public class DbManager {
    private static final String _SC_DB_NAME = "soft_chain.db";
    private static final String _SC_DB_CREATE_ACCOUNT_TABLE =
            "create table account_info (" +
                    "id integer PRIMARY KEY autoincrement," +
                    "phone text," +
                    "addr text," +
                    "balance int)";
    private SQLiteDatabase _db;

    private static DbManager ourInstance = new DbManager();

    public static DbManager getInstance() {
        return ourInstance;
    }

    private DbManager() {
    }

    public void createDb() {
        try {
            _db = SQLiteDatabase.openOrCreateDatabase(_SC_DB_NAME, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createTable() {
        _db.execSQL(_SC_DB_CREATE_ACCOUNT_TABLE);
    }
}
