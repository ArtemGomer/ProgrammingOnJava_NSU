package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import calculator.calculatorExceptions.DivisionByZeroException;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Division extends Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());

    @Override
    public void doOperation(Context context, String[] args) throws CalculatorException {
        this.isValid(context, args);
        Double numerator = context.popValue();
        Double denominator = context.popValue();
        if (denominator == 0) {
            context.pushValue(denominator);
            context.pushValue(numerator);
            throw new DivisionByZeroException("Division by zero in " + this.getClass().getName() + " command");
        } else {
            context.pushValue(numerator / denominator);
        }
        logger.log(Level.FINE, "Successfully did operation {0}", this.getClass().getName());
    }
}

