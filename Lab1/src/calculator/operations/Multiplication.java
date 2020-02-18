package calculator.operations;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Multiplication extends Validator implements Operation{
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) {
        Stack<Double> numbers = context.getNumbers();
        if (this.isValid(context, args)) {
            numbers.push(numbers.pop() * numbers.pop());
            logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
        }
    }
}
