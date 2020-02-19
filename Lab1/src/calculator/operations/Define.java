package calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Define implements Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) {
        double value;
        try {
            Double.parseDouble(args[0]);
        } catch (NumberFormatException ex1) {
            try {
                value = Double.parseDouble(args[1]);
            } catch (NumberFormatException ex2) {
                logger.log(Level.WARNING,"Can not define {0} as {1}", new Object[]{args[0], args[1]});
                return;
            }
            context.setVariable(args[0], value);
            logger.log(Level.FINE,"Successfully defined {0} as {1}", new Object[]{args[0], args[1]});
            return;
        }
        logger.log(Level.WARNING,"Can not define {0} as {1}", new Object[]{args[0], args[1]});
    }
}
