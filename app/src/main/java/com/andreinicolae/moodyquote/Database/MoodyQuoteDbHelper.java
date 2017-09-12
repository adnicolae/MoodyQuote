package com.andreinicolae.moodyquote.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.andreinicolae.moodyquote.Database.QuoteDbContract.QuoteEntry;

/**
 *  SQL Helper Class to create and maintain the database and tables.
 */

// TODO: Refactor
public class MoodyQuoteDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Quote.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + QuoteEntry.TABLE_NAME + " (" +
            QuoteEntry._ID + " INTEGER PRIMARY KEY," +
            QuoteEntry.COL_AUTHOR+ " VARCHAR2(20),"+
            QuoteEntry.COL_QUOTE + " TEXT,"+
            QuoteEntry.COL_MOOD + " VARCHAR2(15))";

    private static final String SQL_DELETE_ENTRIES =
            "DELETE TABLE IF EXISTS " + QuoteEntry.TABLE_NAME;

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

    public boolean insertData(String author, String quote, String mood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QuoteEntry.COL_AUTHOR, author);
        values.put(QuoteEntry.COL_QUOTE, quote);
        values.put(QuoteEntry.COL_MOOD, mood);

        /* Insert the new row
        *  Returns the primary key value of the new row
        */
        long newRow = db.insert(QuoteEntry.TABLE_NAME, null, values);

        if (newRow == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuoteEntry.TABLE_NAME, null);
        return cursor;
    }

    public Cursor readData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
            QuoteEntry.COL_AUTHOR,
            QuoteEntry.COL_QUOTE
        };

        String selection = QuoteEntry.COL_MOOD + " = ?" ;
        String[] selectionArgs = { "Nostalgic" };

        String sortOrder = "RANDOM() LIMIT 1";

        Cursor cursor = db.query(
                QuoteEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        return cursor;
    }

}
