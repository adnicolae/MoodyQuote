package com.andreinicolae.moodyquote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MoodyQuoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moody_quote);
    }
}

//database setup then random on ID where mood = 1 / 2 / 3 /4