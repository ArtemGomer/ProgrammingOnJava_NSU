package calculator.operations;

import calculator.calculatorExceptions.*;

public abstract class Operation {


    public abstract void  doOperation(Context context, String[] args) throws CalculatorException;

    protected void isValid(Context context, String[] args) throws CalculatorException {
        if (args.length != 0){
            throw new WrongArgumentsException("Too many arguments");
        }

        if (context.getStackSize() < 2){
            throw new StackSizeException("Too few elements on stack.( >= 2 needed)");
        }
    }
}
