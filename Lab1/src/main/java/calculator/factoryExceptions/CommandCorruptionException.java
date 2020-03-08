package calculator.factoryExceptions;

public class CommandCorruptionException extends FactoryException {
    public CommandCorruptionException() {
        super();
    }

    public CommandCorruptionException(String message) {
        super(message);
    }

    public CommandCorruptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandCorruptionException(Throwable cause) {
        super(cause);
    }
}
