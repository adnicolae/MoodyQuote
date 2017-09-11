package com.andreinicolae.moodyquote.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *  SQL Helper to create and maintain the database and tables.
 */

public class MoodyQuoteDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Quote.db";
    public static final String TABLE_NAME =  "quote_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Author";
    public static final String COL_3 = "Quote";
    public static final String COL_4 = "Mood";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
            COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_2 + " VARCHAR2(20),"+
            COL_3 + " TEXT,"+
            COL_4 + " VARCHAR2(15))";

    private static final String SQL_DELETE_ENTRIES =
            "DELETE TABLE IF EXISTS " + TABLE_NAME;

    public MoodyQuoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
