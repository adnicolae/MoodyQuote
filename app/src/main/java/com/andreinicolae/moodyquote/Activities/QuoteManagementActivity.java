package com.andreinicolae.moodyquote.Activities;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
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

public class QuoteManagementActivity extends AppCompatActivity {
    MoodyQuoteDbHelper mDbHelper;
    Button insertBtn, viewDataBtn;
    EditText editAuthor, editQuote, editMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_management);

        // Define action bar behaviour.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Quote Management");
        }

        mDbHelper = new MoodyQuoteDbHelper(this);
        editAuthor = (EditText) findViewById(R.id.editAuthor);
        editQuote = (EditText) findViewById(R.id.editQuote);
        editMood = (EditText) findViewById(R.id.editMood);
        insertBtn = (Button) findViewById(R.id.insertButton);
        viewDataBtn = (Button) findViewById(R.id.viewAllButton);

        // Enable data insertion and retrieval functionality.
        insertData();
        viewData();
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
                            Toast.makeText(QuoteManagementActivity.this, "Data inserted.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(QuoteManagementActivity.this, "Data NOT inserted.", Toast.LENGTH_LONG).show();
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
                    }
                }
        );
    }

    /**
     * Create alert dialog containing the quote data.
     * @param title Dialog title.
     * @param message Data to be displayed.
     */
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
