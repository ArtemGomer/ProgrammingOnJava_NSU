package parser;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Path;

public class Reporter {
    public void getReport(WordStatistic statistic, Path outputPath) throws FileNotFoundException {
        PrintStream writer = new PrintStream(outputPath.toFile());
        CSVFormatter formatter = new CSVFormatter();
        String outputString = formatter.format(statistic);
        writer.println("Word;Count;Frequency");
        writer.print(outputString);
    }
}
