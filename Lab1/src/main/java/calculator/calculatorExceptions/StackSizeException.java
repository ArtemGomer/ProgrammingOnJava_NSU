package calculator.calculatorExceptions;

public class StackSizeException extends CalculatorException{
    public StackSizeException() {
        super();
    }

    public StackSizeException(String message) {
        super(message);
    }

    public StackSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public StackSizeException(Throwable cause) {
        super(cause);
    }
}
