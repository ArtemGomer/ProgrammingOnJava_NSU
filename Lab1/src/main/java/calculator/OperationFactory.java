package calculator;

import calculator.operations.Operation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import calculator.factoryExceptions.*;

public class OperationFactory {

    private OperationFactory() throws LoadingPropertiesException {
        try (InputStream in = Calculator.class.getClassLoader().getResourceAsStream("factoryConfig.properties")){
            if (in == null){
                throw new IOException();
            } else {
                properties.load(in);
            }
        } catch (IOException ex) {
            throw new LoadingPropertiesException("Can not load properties");
        }
    }

    private static volatile OperationFactory Instance;
    public static OperationFactory getInstance() throws LoadingPropertiesException {
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

    public Operation createOperation(String operationName) throws CommandCorruptionException, UnknownCommandException{
        String className = properties.getProperty(operationName);
        if (className == null){
            throw new UnknownCommandException("Unknown command : " + operationName);
        }
        try {
            return (Operation) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception ex){
            throw new CommandCorruptionException("Can not create class for command :" + operationName);
        }
    }
}
