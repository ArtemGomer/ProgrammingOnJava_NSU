package calculator;

import calculator.factoryExceptions.CommandCorruptionException;
import calculator.factoryExceptions.FactoryException;
import calculator.factoryExceptions.LoadingPropertiesException;
import calculator.factoryExceptions.UnknownCommandException;
import calculator.operations.*;
import calculator.calculatorExceptions.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {
    private final static Logger logger = Logger.getLogger(Calculator.class.getName());
    Context context = new Context();

    public void calculate(String inFileName) throws IOException, LoadingPropertiesException {
        logger.log(Level.FINE,"Enter calculator.calculate({0})", inFileName);
        Scanner reader = this.createScanner(inFileName);
        while (reader.hasNext()) {
            String line = reader.nextLine();
            if (!line.equals("END")) {
                if (!line.startsWith("#")) {
                    String[] tokens = line.split("\\s+");
                    String operationName = tokens[0];
                    String[] arguments = new String[tokens.length - 1];
                    System.arraycopy(tokens, 1, arguments, 0, tokens.length - 1);
                    Operation operation;
                    logger.log(Level.FINE,"Trying to create class for command {0}", operationName);
                    try {
                        operation = OperationFactory.getInstance().createOperation(operationName);
                    } catch (CommandCorruptionException ex){
                        logger.log(Level.WARNING, "Can not create class", ex);
                        continue;
                    } catch (UnknownCommandException ex){
                        logger.log(Level.WARNING, "Unknown command", ex);
                        continue;
                    }
                    logger.log(Level.FINE, "Successfully created class {0}", operation.getClass().getName());
                    logger.log(Level.FINE, "Enter doOperation of {0}", operation.getClass().getName());
                    try {
                        operation.doOperation(context, arguments);
                    } catch (CalculatorException ex){
                        logger.log(Level.WARNING,"Operation exception", ex);
                    }
                }
            } else {
                logger.log(Level.FINE,"Reached \"END\" command.");
                return;
            }
        }
    }

    private Scanner createScanner(String inFileName) throws IOException {
        if (inFileName == null) {
            logger.log(Level.FINE,"Create reader from System.in");
            return new Scanner(System.in);
        } else {
            logger.log(Level.FINE,"Create reader from {0}.", inFileName);
            return new Scanner(Paths.get("src/main/resources/" + inFileName));
        }
    }
}
