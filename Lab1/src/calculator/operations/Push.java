package calculator.operations;

import calculator.calculatorExceptions.WrongArgumentsException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Push extends Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) throws WrongArgumentsException {
        double value;
        try {
            value = Double.parseDouble(args[0]);
        } catch (NumberFormatException ex) {
            if (context.isDefined(args[0])) {
                context.pushValue(context.getVariableValue(args[0]));
            } else {
                throw new WrongArgumentsException("Variable " + args[0] + " is not defined");
            }
            return;
        }
        context.pushValue(value);
        logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
    }
}
