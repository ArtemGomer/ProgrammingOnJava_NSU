package calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Multiplication extends Operation{
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) {
        if (this.isValid(context, args)) {
            context.pushValue(context.popValue() * context.popValue());
            logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
        }
    }
}
