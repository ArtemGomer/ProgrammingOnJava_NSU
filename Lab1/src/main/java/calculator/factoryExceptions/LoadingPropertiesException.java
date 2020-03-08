package calculator.factoryExceptions;

public class LoadingPropertiesException extends FactoryException {
    public LoadingPropertiesException() {
        super();
    }

    public LoadingPropertiesException(String message) {
        super(message);
    }

    public LoadingPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadingPropertiesException(Throwable cause) {
        super(cause);
    }
}
