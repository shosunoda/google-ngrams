package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    Mapper poo;
    NGramMap apple;
    public HyponymsHandler(Mapper map, NGramMap pineapple) {
        poo = map;
        apple = pineapple;

    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        String moo = (words.get(0));
        String moop = poo.wordmap.results(poo.wordmap.traversal(moo));
        String moops = moop.replace("[", "").replace("]", "");
        Set<String> set1 = new HashSet<>(Set.of(moops.split(",\\s*")));
        for (String no : words) {
            String temp = poo.wordmap.results(poo.wordmap.traversal(no));
            String temps = temp.replace("[", "").replace("]", "");
            Set<String> set2 = new HashSet<>(Set.of(temps.split(",\\s*")));
            set1.retainAll(set2);
        }


        Set<String> wordscommon = new TreeSet<>(set1);
        String pleaseprayingemoji = wordscommon.toString();
        String emoji = pleaseprayingemoji.replace("[", "").replace("]", "");

        if (q.k() != 0) {
            String[] wordlist = emoji.split(",\\s*");
            Double[] biggestsize = new Double[q.k()];
            Arrays.fill(biggestsize, 0.0);
            String[] newwordlist = new String[q.k()];
            for (String myword : wordlist) {
                TimeSeries temporary = apple.countHistory(myword, q.startYear(), q.endYear());
                double tempsum = temporary.values().stream().mapToDouble(Double::doubleValue).sum();
                double smallest = Double.MAX_VALUE;
                int smallestIndex = -1;
                for (int i = 0; i < biggestsize.length; i++) {
                    double current = biggestsize[i];
                    if (current < smallest) {
                        smallest = current;
                        smallestIndex = i;
                    }
                }
                if (tempsum > smallest) {
                    biggestsize[smallestIndex] = tempsum;
                    newwordlist[smallestIndex] = myword;
                }

            }
            ArrayList<String> result = new ArrayList<>();
            for (String element : newwordlist) {
                if (element != null) {
                    result.add(element);
                }
            }
            Collections.sort(result);
            String poops = result.toString();
            return poops;

        }
        Set<String> commonWords = new TreeSet<>(set1);
        return commonWords.toString();
    }
}


