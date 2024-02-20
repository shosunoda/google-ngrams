package main;

import edu.princeton.cs.algs4.In;

import ngrams.TimeSeries;

public class Mapper {
    Graph2lol wordmap;

    public Mapper(String wordsFilename, String countsFilename) {
        wordmap = new Graph2lol();
        In synsets = new In(wordsFilename);
        In hyponyms = new In(countsFilename);
        while (!synsets.isEmpty()) {
            String line = synsets.readLine();
            String[] parts = line.split(",");
            int key = Integer.parseInt(parts[0].trim());
            String value = (parts[1].trim());
            wordmap.addWord(key, value);
        }
        while (!hyponyms.isEmpty()) {
            String line = hyponyms.readLine();
            String[] parts = line.split(",");
            int firstkey = Integer.parseInt(parts[0].trim());
            for (int i = 1; i < parts.length; i += 1) {
                wordmap.addConnection(firstkey, Integer.parseInt(parts[i].trim()));
            }

        }
    }
}

