package com.andreinicolae.moodyquote.Models;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Class to model a Quote-Author pair.
 */

public class QuoteModel {
    private String author;
    private String quote;

    public QuoteModel() {
    }

    public QuoteModel(String author, String quote) {
        this.author = author;
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void showQuoteDialog(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
