package calculator;

import calculator.operations.Operation;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperationFactory {
    private final static Logger logger = Logger.getLogger(OperationFactory.class.getName());

    private OperationFactory() {
        try {
            properties.load(Calculator.class.getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            logger.log(Level.SEVERE,"Can not load properties.", ex);
            System.exit(0);
        }
    }

    private static OperationFactory Instance;
    public static OperationFactory getInstance(){
        if (Instance == null){
            Instance = new OperationFactory();
        }
        return Instance;
    }

    private final Properties properties = new Properties();

    public Operation createOperation(String operationName) {
        String className = properties.getProperty(operationName);
        logger.log(Level.FINE,"Trying to create class for command {0}", operationName);
        Operation operation = null;
        try {
            operation = (Operation) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            logger.log(Level.WARNING,"Can not create class.");
        }
        if (operation != null) {
            logger.log(Level.FINE, "Created operation {0}", className);
        }
        return operation;
    }
}
