package com.andreinicolae.moodyquote.Activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andreinicolae.moodyquote.Database.MoodyQuoteDbHelper;
import com.andreinicolae.moodyquote.R;

import java.util.HashMap;

import static com.andreinicolae.moodyquote.R.id.showQuote;

public class MoodyQuoteActivity extends AppCompatActivity {
    Button nostalgicBtn, sadBtn, fearfulBtn, doubtfulBtn;
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

        Button[] buttons = new Button[] { nostalgicBtn, sadBtn, fearfulBtn, doubtfulBtn };

        for (Button btn : buttons) {
            showQuote(btn);
        }
    }

    public void showQuote(final Button btn) {
        btn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick (View v) {
                        HashMap<String, String> quotes = viewSelection(btn.getText().toString());
                        Toast.makeText(MoodyQuoteActivity.this, quotes.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public HashMap<String, String> viewSelection(String mood) {
        Cursor cursor = mDbHelper.readData(mood);

        HashMap<String, String> quoteMap = new HashMap<>();

        while (cursor.moveToNext()) {
            quoteMap.put(cursor.getString(0), cursor.getString(1));
        }
        cursor.close();

        return quoteMap;
    }
}
