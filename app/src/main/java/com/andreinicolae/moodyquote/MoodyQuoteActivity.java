package com.andreinicolae.moodyquote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andreinicolae.moodyquote.Database.MoodyQuoteDbHelper;

public class MoodyQuoteActivity extends AppCompatActivity {
    MoodyQuoteDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moody_quote);
        mDbHelper = new MoodyQuoteDbHelper(this);
    }
}
