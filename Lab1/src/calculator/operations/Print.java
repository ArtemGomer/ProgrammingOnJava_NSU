package calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Print extends Operation {
    private final static Logger logger = Logger.getLogger(Addition.class.getName());
    @Override
    public void doOperation(Context context, String[] args) {
        if (this.isValid(context, args)){
            System.out.println(context.getValue());
        }
    }

    @Override
     protected boolean isValid(Context context, String[] args){
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
