import Factory.Factory;

import java.io.IOException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Trying to create factory.");
        try {
            Scanner scanner = new Scanner(System.in);
            Factory factory = new Factory();
            logger.info("Factory successfully created. Start of factory.");
            factory.start();
            if (scanner.next().equals("end")){
                logger.info("Stop of factory.");
                factory.stop();
                System.exit(0);
            }
        } catch (IOException ex){
            logger.error("Can not create factory.", ex);
            System.exit(1);
        }
    }
}
