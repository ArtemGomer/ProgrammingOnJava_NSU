package calculator.calculatorExceptions;

public class WrongArgumentsException extends CalculatorException{
    public WrongArgumentsException() {
        super();
    }

    public WrongArgumentsException(String message) {
        super(message);
    }

    public WrongArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongArgumentsException(Throwable cause) {
        super(cause);
    }
}
