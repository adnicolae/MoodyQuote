package com.andreinicolae.moodyquote.Data;

import com.andreinicolae.moodyquote.Models.Quote;

/**
 * Created by Andrei Nicolae on 9/17/2017.
 */

public class QuoteRepository {
    private static Quote[] _quotes = new Quote[]  {
            new Quote("Winston Churchill", "A pessimist sees the difficulty in every opportunity; an optimist sees the opportunity in every difficulty.", "Doubtful"),
            new Quote("Dr. Seuss", "Don’t cry because it’s over, smile because it happened.", "Nostalgic"),
            new Quote("Oscar Wilde", "We are all in the gutter, but some of us are looking at the stars.", "Fearful"),
            new Quote("Anne Frank", "How wonderful it is that nobody need wait a single moment before starting to improve the world.", "Doubtful"),
            new Quote("J.R.R. Tolkien", "There is some good in this world, and it’s worth fighting for.", "Doubtful"),
            new Quote("Marcus Aurelius", "Dwell on the beauty of life. Watch the stars, and see yourself running with them.", "Sad"),
            new Quote("Cassandra Clare", "As long as there was coffee in the world, how bad could things be?", "Fearful"),
            new Quote("Deepak Chopra", "The best way to get rid of the pain is to feel the pain. And when you feel the pain and go beyond it, " +
                    "you'll see there's a very intense love that is wanting to awaken itself.", "Sad")
    };

    public QuoteRepository() {
    }

    public Quote[] getQuotes() {
        return _quotes;
    }
}
