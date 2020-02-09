import java.nio.file.Paths;
import java.nio.file.Path;

import parser.Controller;

public class Main {
    public static void main(String[] args) {
        Path inputPath = Paths.get(args[0]).toAbsolutePath();
        Path outputPath = Paths.get(args[1]).toAbsolutePath();
        Controller controller = new Controller(inputPath, outputPath);
        try {
            controller.process();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
