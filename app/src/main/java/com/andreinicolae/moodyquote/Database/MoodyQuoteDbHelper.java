package com.andreinicolae.moodyquote.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Quote.db
 * ID - Author - Quote - Mood
 * Created by Andrei Nicolae on 9/11/2017.
 */

public class MoodyQuoteDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Quote.db";
    public static final String TABLE_NAME =  "quote_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Author";
    public static final String COL_3 = "Quote";
    public static final String COL_4 = "Mood";

    public MoodyQuoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
