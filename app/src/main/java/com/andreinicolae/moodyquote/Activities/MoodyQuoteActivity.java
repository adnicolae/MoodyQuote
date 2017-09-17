package com.andreinicolae.moodyquote.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andreinicolae.moodyquote.Database.MoodyQuoteDbHelper;
import com.andreinicolae.moodyquote.Models.QuoteModel;
import com.andreinicolae.moodyquote.R;

import java.util.HashMap;

import static com.andreinicolae.moodyquote.R.id.showQuote;

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

    public void showQuote(final Button btn) {
        btn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick (View v) {
                        QuoteModel quotes = viewSelection(btn.getText().toString());
                        quotes.showQuoteDialog("Here is a quote to make you feel better: ", "\"" + quotes.getQuote() + "\" \n by " + quotes.getAuthor(), v.getContext());
                    }
                }
        );
    }

    public QuoteModel viewSelection(String mood) {
        Cursor cursor = mDbHelper.readData(mood);

        QuoteModel quotes = new QuoteModel();

        while (cursor.moveToNext()) {
            quotes.setAuthor(cursor.getString(0));
            quotes.setQuote(cursor.getString(1));
        }
        cursor.close();

        return quotes;
    }
}
