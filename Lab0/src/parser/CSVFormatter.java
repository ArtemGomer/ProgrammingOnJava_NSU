package parser;

import java.util.Map;

class CSVFormatter implements Formatter {
    @Override
    public String format(WordStatistic statistic) {
        StringBuilder outputString = new StringBuilder();
        for (Map.Entry<String, Integer> pair : statistic.getSortedStatistic()) {
            outputString.append(pair.getKey()).append(';').append(pair.getValue());
            outputString.append(';').append(String.format("%.2f", statistic.getFrequency(pair.getValue()))).append('%').append('\n');
        }
        return outputString.toString();
    }
}
