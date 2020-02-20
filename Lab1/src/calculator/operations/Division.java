package calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Division extends Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) {
        if (this.isValid(context, args)) {
            Double numerator = context.popValue();
            Double denominator = context.popValue();
            if (denominator == 0){
                System.out.println("Division by zero");
                context.pushValue(denominator);
                context.pushValue(numerator);
            } else {
                context.pushValue(numerator / denominator);
            }
            logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
        }
    }
}

