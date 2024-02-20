package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.TreeMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    TreeMap<String, TimeSeries> poo;
    TimeSeries poo2;
    TimeSeries moo;


    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        poo2 = new TimeSeries();
        poo = new TreeMap<String, TimeSeries>();
        In wordsIn = new In(wordsFilename);
        In countsIn = new In(countsFilename);

        while (!wordsIn.isEmpty()) {
            String key = wordsIn.readString();
            if (!poo.containsKey(key)) {
                poo.put(key, new TimeSeries());
            }
            String fake = wordsIn.readString();
            int date = Integer.parseInt(fake);
            String fake2 = wordsIn.readString();
            Double number = Double.parseDouble(fake2);
            poo.get(key).put(date, number);
            wordsIn.readLine();
        }
        while (!countsIn.isEmpty()) {
            String line = countsIn.readLine();
            String[] parts = line.split(",");
            int year = Integer.parseInt(parts[0].trim());
            double total = Double.parseDouble(parts[1].trim());
            poo2.put(year, total);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        if (!poo.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries temp = poo.get(word);
        return new TimeSeries(temp, startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        if (!poo.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries temp = poo.get(word);
        return new TimeSeries(temp, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return new TimeSeries(poo2, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!poo.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries temp = new TimeSeries(poo.get(word), startYear, endYear);
        return temp.dividedBy(poo2);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (!poo.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries temp = new TimeSeries(poo.get(word), MIN_YEAR, MAX_YEAR);
        return temp.dividedBy(poo2);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries sum = new TimeSeries();
        for (String word : words) {
            if (poo.containsKey(word)) {
                TimeSeries wordData = poo.get(word); /** this line gives you the timeseries for the word */
                for (int year = startYear; year <= endYear; year += 1) {
                    if (wordData.containsKey(year)) {
                        double add = wordData.get(year);
                        if (sum.containsKey(year)) {
                            sum.put(year, sum.get(year) + add);
                        } else {
                            sum.put(year, add);
                        }
                    }
                }
            }
        }
        return sum.dividedBy(poo2);
    }

    public TimeSeries summedWeightHistory(Collection<String> words) {
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
    }
}
