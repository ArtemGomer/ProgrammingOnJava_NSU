package calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Push implements Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) {
        double value;
        try {
            value = Double.parseDouble(args[0]);
        } catch (NumberFormatException ex) {
            if (context.isDefined(args[0])) {
                context.pushValue(context.getVariableValue(args[0]));
            } else {
                logger.log(Level.WARNING, "Can not push {0} on stack", args[0]);
            }
            return;
        }
        context.pushValue(value);
        logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
    }
}
