package com.andreinicolae.moodyquote.Models;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Class to model a Quote-Author pair.
 */

public class Quote {
    private String author;
    private String quote;
    private String mood;

    public Quote() {
    }

    public Quote(String author, String quote, String mood) {
        this.author = author;
        this.quote = quote;
        this.mood = mood;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }

    public String getMood() { return mood; }

    public void setMood(String mood) { this.mood = mood; }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void showQuoteDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Here is a quote to make you feel better: ");
        builder.setMessage("\"" + this.getQuote() + "\"\n\nby " + this.getAuthor());
        builder.show();
    }
}
