package calculator.operations;

import calculator.calculatorExceptions.*;

public abstract class Operation {


    public abstract void  doOperation(Context context, String[] args) throws CalculatorException;

    protected void isValid(Context context, String[] args) throws CalculatorException {
        if (args.length != 0){
            throw new WrongArgumentsException("Too many arguments");
//            logger.log(Level.WARNING,"Wrong arguments for {0} command", this.getClass().getName());
        }

        if (context.getStackSize() < 2){
//            logger.log(Level.WARNING,"Too few elements on stack for {0} command", this.getClass().getName());
            throw new StackSizeException("Too few elements on stack.( >= 2 needed)");
        }
    }
}
