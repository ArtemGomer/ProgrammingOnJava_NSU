package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Subtraction extends Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());

    @Override
    public void doOperation(Context context, String[] args) throws CalculatorException {
        this.isValid(context, args);
        context.pushValue(context.popValue() - context.popValue());
        logger.log(Level.FINE, "Successfully did operation {0}", this.getClass().getName());

    }
}
