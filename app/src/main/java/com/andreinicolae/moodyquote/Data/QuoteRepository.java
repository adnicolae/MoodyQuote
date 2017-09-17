package com.andreinicolae.moodyquote.Data;

import com.andreinicolae.moodyquote.Models.Quote;

/**
 * Repository class to organise quote data.
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
                    "you'll see there's a very intense love that is wanting to awaken itself.", "Sad"),
            new Quote("Nizami", "In the hour of adversity, be not without hope; for crystal rain falls from black clouds.", "Doubtful"),
            new Quote("Unknown", "A healthy attitude is contagious but don’t wait to catch it from others. Be a carrier.", "Sad"),
            new Quote("Robert Jordan", "Always something new, always something I didn’t expect, and sometimes it isn’t horrible.", "Nostalgic"),
            new Quote("Steve Voake", "Relax. They’re not going to kill us. They’re going to TRY and kill us. And that is a very different thing.", "Fearful"),
            new Quote("Barack Obama", "Don’t let your failures define you—let them teach you.", "Doubtful"),
            new Quote("Rick Steves", "Be fanatically positive and militantly optimistic. If something is not to your liking, change your liking.", "Sad"),
            new Quote("Arthur C. Clarke", "I am an optimist. Anyone interested in the future has to be otherwise he would simply shoot himself.", "Sad"),
            new Quote("Emily Dana", "Everything great was accomplished by fools who dreamed.", "Doubtful"),
            new Quote("Rohit Pandita", "I am enjoying my life because things aren’t going the way I planned.", "Doubtful")
    };

    public QuoteRepository() {
    }

    public Quote[] getQuotes() {
        return _quotes;
    }
}
