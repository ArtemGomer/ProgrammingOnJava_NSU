package calculator.operations;

import calculator.calculatorExceptions.WrongArgumentsException;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Define extends Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) throws WrongArgumentsException {
        double value;
        try {
            Double.parseDouble(args[0]);
        } catch (NumberFormatException ex1) {
            try {
                value = Double.parseDouble(args[1]);
            } catch (NumberFormatException ex2) {
                throw new WrongArgumentsException("Can not define " + args[0] + " as " + args[1]);
            }
            context.setVariable(args[0], value);
            logger.log(Level.FINE,"Successfully defined {0} as {1}", new Object[]{args[0], args[1]});
            return;
        }
        throw new WrongArgumentsException("Can not define " + args[0] + " as " + args[1]);
    }
}
