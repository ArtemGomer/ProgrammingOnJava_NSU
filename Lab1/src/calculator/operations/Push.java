package calculator.operations;

import java.util.Map;
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
            Map<String, Double> variables = context.getVariables();
            if (variables.containsKey(args[0])) {
                context.getNumbers().push(variables.get(args[0]));
            } else {
                logger.log(Level.WARNING, "Can not push {0} on stack", args[0]);
            }
            return;
        }
        context.getNumbers().push(value);
        logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
    }
}
