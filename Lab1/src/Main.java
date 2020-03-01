import calculator.Calculator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        LogManager logManager = LogManager.getLogManager();
        try {
            logManager.readConfiguration(new FileInputStream("logConfig.properties"));
        } catch (IOException ex){
            logger.log(Level.WARNING, "Can not read properties for logging");
        }
        String inFileName = null;
        if (args.length > 1) {
            logger.log(Level.SEVERE,"Too much arguments for programme {0}. End of work.", Arrays.toString(args));
            return;
        } else if (args.length == 1) {
            inFileName = args[0];
        }
        Calculator calculator = new Calculator();
        logger.log(Level.FINE, "Create calculator");
        try {
            calculator.calculate(inFileName);
        } catch (IOException ex) {
            logger.log(Level.SEVERE,"Can not open file.", ex);
            return;
        }
        logger.log(Level.INFO, "Successfully ended calculation!");
    }
}
