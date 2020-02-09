package parser;

import java.nio.file.Path;

public class Controller {
    private final Path inputPath;
    private final Path outputPath;

    public Controller(Path input, Path output) {
        this.inputPath = input;
        this.outputPath = output;
    }

    public void process() throws Exception {
        Reader reader = new Reader(inputPath);
        WordStatistic statistic = reader.read();
        Reporter reporter = new Reporter();
        reporter.getReport(statistic, outputPath);
    }
}
