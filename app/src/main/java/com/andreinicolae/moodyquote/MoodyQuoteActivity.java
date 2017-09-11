package com.andreinicolae.moodyquote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andreinicolae.moodyquote.Database.MoodyQuoteDbHelper;

import static com.andreinicolae.moodyquote.R.styleable.View;

public class MoodyQuoteActivity extends AppCompatActivity {
    MoodyQuoteDbHelper mDbHelper;
    Button insertButton;
    EditText editAuthor, editQuote, editMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moody_quote);
        mDbHelper = new MoodyQuoteDbHelper(this);

        editAuthor = (EditText) findViewById(R.id.editAuthor);
        editQuote = (EditText) findViewById(R.id.editQuote);
        editMood = (EditText) findViewById(R.id.editMood);
        insertButton = (Button) findViewById(R.id.insertButton);
        insertData();
    }

    public void insertData() {
        insertButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v){
                        boolean isInserted = mDbHelper.insertData(
                                editAuthor.getText().toString(),
                                editQuote.getText().toString(),
                                editMood.getText().toString()
                        );

                        if (isInserted) {
                            Toast.makeText(MoodyQuoteActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MoodyQuoteActivity.this, "Data NOT inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

}
