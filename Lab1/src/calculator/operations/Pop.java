package calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Pop extends Validator implements Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());

    @Override
    public void doOperation(Context context, String[] args) {
        if (this.isValid(context, args)) {
            context.popValue();
            logger.log(Level.FINE,"Successfully did operation {0}", this.getClass().getName());
        }
    }

    @Override
    boolean isValid(Context context, String[] args){
        if (args.length != 0){
            logger.log(Level.WARNING,"Wrong arguments for {0} command", this.getClass().getName());
            return false;
        }
        if (context.getStackSize() == 0){
            logger.log(Level.WARNING,"Too few elements on stack for {0} command", this.getClass().getName());
            return false;
        }
        return true;
    }

}
