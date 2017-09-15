package com.andreinicolae.moodyquote.Models;

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
}
