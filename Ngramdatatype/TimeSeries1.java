package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.

 */
public class TimeSeries extends TreeMap<Integer, Double> {

    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (int year :ts.keySet()) {
            if (year >= startYear && year <= endYear) {
                this.put(year, ts.get(year));
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> poo = new ArrayList<>();
        for (int year: this.keySet()) {
            poo.add(year);
        }
        return poo;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> poo = new ArrayList<>();
        for (int year: this.keySet()) {
            poo.add(this.get(year));
        }
        return poo;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries poo = new TimeSeries();
        for (int year : ts.keySet()) {
            Double x1 = ts.get(year);
            if (this.containsKey(year)) {
                poo.put(year, x1 + this.get(year));
            }
            this.put(year, x1);
        }
        for (int year2 : this.keySet()) {
            if (!poo.containsKey(year2)) {
                poo.put(year2, this.get(year2));
            }
        }
        return poo;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries poo = new TimeSeries();
        for (int year : this.keySet()) {
            Double x1 = this.get(year);
            if (!ts.containsKey(year)) {
                throw new IllegalArgumentException("Missing year");
            }
            if (ts.get(year) == 0) {
                throw new IllegalArgumentException("Division by zero error at year ");
            }
            poo.put(year, x1 / ts.get(year));
        }
        return poo;
    }
}
