package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import calculator.calculatorExceptions.StackSizeException;
import calculator.calculatorExceptions.WrongArgumentsException;

public final class Print extends Operation {

    @Override
    public void doOperation(Context context, String[] args) throws CalculatorException {
        this.isValid(context, args);
        System.out.println(context.getValue());
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
