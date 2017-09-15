package com.andreinicolae.moodyquote.Activities;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andreinicolae.moodyquote.Database.MoodyQuoteDbHelper;
import com.andreinicolae.moodyquote.R;

import java.util.HashMap;

//TODO: Fix Spinner
//TODO: Break down activities
public class QuoteManagementActivity extends AppCompatActivity {
    MoodyQuoteDbHelper mDbHelper;
    Button insertBtn, viewDataBtn, nostalgicBtn;
    TextView showQuote;
    EditText editAuthor, editQuote, editMood;
    Spinner moodSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moody_quote);
        mDbHelper = new MoodyQuoteDbHelper(this);

        editAuthor = (EditText) findViewById(R.id.editAuthor);
        editQuote = (EditText) findViewById(R.id.editQuote);
        editMood = (EditText) findViewById(R.id.editMood);
        insertBtn = (Button) findViewById(R.id.insertButton);
        viewDataBtn = (Button) findViewById(R.id.viewAllButton);
        showQuote = (TextView) findViewById(R.id.showQuote);
        nostalgicBtn = (Button) findViewById(R.id.nostalgicBtn);
        moodSpinner = (Spinner) findViewById(R.id.moodSpinner);


        insertData();
        viewData();
        showQuote(nostalgicBtn);
    }

    public void insertData() {
        insertBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v){
                        boolean isInserted = mDbHelper.insertData(
                                editAuthor.getText().toString(),
                                editQuote.getText().toString(),
                                editMood.getText().toString()
                        );

                        if (isInserted) {
                            Toast.makeText(QuoteManagementActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(QuoteManagementActivity.this, "Data NOT inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }



    public void viewData() {
        viewDataBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v) {
                        Cursor cursor = mDbHelper.getAllData();
                        // Check for data in the database
                        if (cursor.getCount() == 0) {
                            showMessage("Error", "No data found!");
//                            Toast.makeText(QuoteManagementActivity.this, "NO DATA", Toast.LENGTH_SHORT).show();
                        }

                        StringBuffer buffer = new StringBuffer();

                        // Append all data to the buffer
                        while (cursor.moveToNext()) {
                            buffer.append("Id: " + cursor.getString(0) + "\n");
                            buffer.append("Author: " + cursor.getString(1) + "\n");
                            buffer.append("Quote: " + cursor.getString(2) + "\n");
                            buffer.append("Mood: " + cursor.getString(3) + "\n \n");
                        }
                        cursor.close();

                        showMessage("Data", buffer.toString());
//                        Toast.makeText(QuoteManagementActivity.this, buffer.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showQuote(final Button btn) {
        btn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick (View v) {
                        HashMap<String, String> quotes = viewSelection(btn.getText().toString());
                        showQuote.setText(quotes.toString());
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

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
