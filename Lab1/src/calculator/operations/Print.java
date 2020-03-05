package calculator.operations;

import calculator.calculatorExceptions.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Print extends Operation {

    private final static Logger logger = Logger.getLogger(Addition.class.getName());

    @Override
    public void doOperation(Context context, String[] args) throws CalculatorException {
        this.isValid(context, args);
        System.out.println(context.getValue());
        logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
    }

    @Override
    protected void isValid(Context context, String[] args) throws CalculatorException {
        if (args.length != 0) {
            throw new WrongArgumentsException("Too many arguments for " + this.getClass().getName() + " command.(0 needed)");
        }
        if (context.getStackSize() == 0) {
            throw new StackSizeException("Too few elements on stack for " + this.getClass().getName() +" ( >= 1 needed)");
        }
    }
}
