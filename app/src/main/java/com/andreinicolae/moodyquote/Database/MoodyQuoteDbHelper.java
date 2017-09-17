package com.andreinicolae.moodyquote.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreinicolae.moodyquote.Data.QuoteRepository;
import com.andreinicolae.moodyquote.Models.Quote;

import static com.andreinicolae.moodyquote.Database.QuoteDbContract.QuoteEntry;

/**
 *  SQL Helper Class to create and maintain the database and tables.
 */
public class MoodyQuoteDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Quote.db";
    private QuoteRepository _quoteRepository = null;

    // Defines the table.
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
        _quoteRepository = new QuoteRepository();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

        for (Quote quote : _quoteRepository.getQuotes()) {
            db.execSQL("INSERT INTO quote_table VALUES (null,\"" + quote.getAuthor() + "\", \"" +
                    quote.getQuote() +"\", \"" + quote.getMood() + "\")");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * Facilitates db insertion of quotes by the user.
     * @param author The quote's author.
     * @param quote The quote's message.
     * @param mood  The quote's mood;
     * @return true if a new database row has been created.
     */
    public boolean insertData(String author, String quote, String mood) {
        if (author.length() < 5 || quote.length() < 5 || mood.length() < 5) {
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QuoteEntry.COL_AUTHOR, author);
        values.put(QuoteEntry.COL_QUOTE, quote);
        values.put(QuoteEntry.COL_MOOD, mood);

        /* Insert the new row
        *  Returns the primary key value of the new row
        */
        long newRow = db.insert(QuoteEntry.TABLE_NAME, null, values);

        return (newRow != -1);
    }

    /**
     * Facilitates entire quote data retrieval.
     * @return A cursor containing the results statements.
     */
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuoteEntry.TABLE_NAME, null);
        return cursor;
    }

    /**
     * Facilitates random quote data retrieval for a given mood.
     * @param mood The mood of the quote to be retrieved.
     * @return A cursor containing result statements.
     */
    public Cursor readData(String mood) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
            QuoteEntry.COL_AUTHOR,
            QuoteEntry.COL_QUOTE
        };

        String selection = QuoteEntry.COL_MOOD + " = ?" ;
        String[] selectionArgs = { mood };

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
