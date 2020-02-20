package calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Operation {
    private static final Logger logger = Logger.getLogger(Addition.class.getName());

    public abstract void  doOperation(Context context, String[] args);

    protected boolean isValid(Context context, String[] args){
        if (args.length != 0){
            logger.log(Level.WARNING,"Wrong arguments for {0} command", this.getClass().getName());
            return false;
        }

        if (context.getStackSize() < 2){
            logger.log(Level.WARNING,"Too few elements on stack for {0} command", this.getClass().getName());
            return false;
        }
        return true;
    }
}
