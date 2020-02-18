package calculator.operations;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sqrt extends Validator implements Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) {
        Stack<Double> numbers = context.getNumbers();
        if (this.isValid(context, args)){
            numbers.push(Math.sqrt(numbers.pop()));
            logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
        }
    }

    @Override
    boolean isValid(Context context, String[] args){
        Stack<Double> numbers = context.getNumbers();
        if (numbers.size() < 1){
            logger.log(Level.WARNING,"Too few elements on stack for {0} command", this.getClass().getName());
            return false;
        }
        if (numbers.peek() < 0){
            logger.log(Level.WARNING, "{0} from negative number", this.getClass().getName());
            return false;
        }
        return true;
    }
}
