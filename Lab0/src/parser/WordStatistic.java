package parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class WordStatistic {
    private int numberOfWords;
    private TreeMap<String, Integer> statistic = new TreeMap<>();
    private ArrayList<Map.Entry<String, Integer>> sortedStatistic = new ArrayList<>();

    public void addToMap(String[] tokens) {
        for (String token : tokens) {
            if (!token.isEmpty()) {
                if (statistic.containsKey(token)) {
                    statistic.replace(token, statistic.get(token) + 1);
                } else {
                    statistic.put(token, 1);
                }
                ++numberOfWords;
            }
        }
    }

    public ArrayList<Map.Entry<String, Integer>> getSortedStatistic() {
        sortedStatistic.addAll(statistic.entrySet());
        sortedStatistic.sort(Map.Entry.comparingByValue());
        Collections.reverse(sortedStatistic);
        return sortedStatistic;
    }

    public double getFrequency(int entry) {
        return (double) entry / numberOfWords * 100;
    }
}
