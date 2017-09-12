package com.andreinicolae.moodyquote.Database;

import android.provider.BaseColumns;

/**
 * Created by Andrei Nicolae on 9/12/2017.
 */

public final class QuoteDbContract {
    private QuoteDbContract() {}

    public static class QuoteEntry implements BaseColumns {
        public static final String TABLE_NAME =  "quote_table";
        public static final String COL_AUTHOR = "Author";
        public static final String COL_QUOTE = "Quote";
        public static final String COL_MOOD = "Mood";
    }
}
