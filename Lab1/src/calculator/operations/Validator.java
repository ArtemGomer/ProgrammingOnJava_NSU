package calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Validator {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    boolean isValid(Context context, String[] args){
        if (args.length != 0){
            logger.log(Level.WARNING,"Wrong arguments for {0} command", this.getClass().getName());
            return false;
        }

        if (context.getNumbers().size() < 2){
            logger.log(Level.WARNING,"Too few elements on stack for {0} command", this.getClass().getName());
            return false;
        }
        return true;
    }
}
