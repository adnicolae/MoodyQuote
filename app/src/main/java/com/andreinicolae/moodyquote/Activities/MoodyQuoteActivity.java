package com.andreinicolae.moodyquote.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andreinicolae.moodyquote.Database.MoodyQuoteDbHelper;
import com.andreinicolae.moodyquote.Models.Quote;
import com.andreinicolae.moodyquote.R;

public class MoodyQuoteActivity extends AppCompatActivity {
    Button nostalgicBtn, sadBtn, fearfulBtn, doubtfulBtn;
    FloatingActionButton floatingBtn;
    MoodyQuoteDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moody_quote);

        mDbHelper = new MoodyQuoteDbHelper(this);
        nostalgicBtn = (Button) findViewById(R.id.btnNostalgic);
        sadBtn = (Button) findViewById(R.id.btnSad);
        fearfulBtn = (Button) findViewById(R.id.btnFearful);
        doubtfulBtn = (Button) findViewById(R.id.btnDoubtful);
        floatingBtn = (FloatingActionButton) findViewById(R.id.flloatingBtn);

        linkActivities();

        Button[] buttons = new Button[] { nostalgicBtn, sadBtn, fearfulBtn, doubtfulBtn };

        for (Button btn : buttons) {
            showQuote(btn);
        }
    }

    /**
     * Define Intent and activity transition on floating action button.
     */
    public void linkActivities() {
        final Intent myIntent = new Intent(MoodyQuoteActivity.this, QuoteManagementActivity.class);
        floatingBtn.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick (View v) {
                        startActivity(myIntent);
                    }
                }
        );
    }

    /**
     * Open an alert dialog containing the quote on button click.
     * @param btn The button that has been clicked.
     */
    public void showQuote(final Button btn) {
        btn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick (View v) {
                        Quote quote = getQuoteBasedOnMood(btn.getText().toString());
                        quote.showQuoteDialog(v.getContext());
                    }
                }
        );
    }

    /**
     * Returns a random quote given a certain mood.
     * @param mood The mood of the quote to be retrieved.
     * @return Random quote by mood.
     */
    public Quote getQuoteBasedOnMood(String mood) {
        Cursor cursor = mDbHelper.readData(mood);

        Quote quotes = new Quote();

        while (cursor.moveToNext()) {
            quotes.setAuthor(cursor.getString(0));
            quotes.setQuote(cursor.getString(1));
        }
        cursor.close();

        return quotes;
    }
}
