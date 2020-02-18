package calculator.operations;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Division extends Validator implements Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) {
        Stack<Double> numbers = context.getNumbers();
        if (this.isValid(context, args)) {
            double numerator = numbers.pop();
            double denominator = numbers.pop();
            if (denominator == 0){
                System.out.println("Division by zero");
                numbers.push(denominator);
                numbers.push(numerator);
            } else {
                numbers.push(numerator / denominator);
            }
            logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
        }
    }
}

