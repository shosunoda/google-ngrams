package main;

import browser.NgordnetQueryHandler;
import browser.NgordnetServer;
import ngrams.NGramMap;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        Mapper shoosh = new Mapper(synsetFile,hyponymFile);
        NGramMap poo = new NGramMap(wordFile,countFile);
        return new HyponymsHandler(shoosh, poo);

    }
}
