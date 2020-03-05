package calculator;

import calculator.operations.Operation;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperationFactory {
    private final static Logger logger = Logger.getLogger(OperationFactory.class.getName());

    private OperationFactory() throws IOException {
        try {
            properties.load(Calculator.class.getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            logger.log(Level.SEVERE,"Can not load properties.", ex);
            //System.exit(0);
            throw ex;
        }
    }

    private static volatile OperationFactory Instance;
    public static OperationFactory getInstance() throws IOException {
        if (Instance == null){
            synchronized (ClassLoader.class){
                if (Instance == null){
                    Instance = new OperationFactory();
                }
            }
        }
        return Instance;
    }

    private final Properties properties = new Properties();

    public Operation createOperation(String operationName) throws Exception{
        String className = properties.getProperty(operationName);
        return (Operation) Class.forName(className).getDeclaredConstructor().newInstance();
    }
}
