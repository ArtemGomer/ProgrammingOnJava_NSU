package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;

public class Reader {
    private final Path inputPath;

    public Reader(Path input) {
        this.inputPath = input;
    }

    public  WordStatistic read() throws IOException {
        WordStatistic statistic = new WordStatistic();
        BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
        while (reader.ready()) {
            String line = reader.readLine();
            String[] tokens = line.toLowerCase().split("[\\W]");
            statistic.addToMap(tokens);
        }
        return statistic;
    }
}
