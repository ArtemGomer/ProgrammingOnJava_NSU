package Factory;

import Details.*;
import FactoryStuff.AutoStorageController;
import FactoryStuff.Dealer;
import FactoryStuff.Supplier;
import Storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Factory {
    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Auto> autoStorage;

    private final ArrayList<Supplier<? extends Detail>> suppliers = new ArrayList<>();
    private final ArrayList<Dealer> dealers = new ArrayList<>();
    private final ExecutorService workers;

    private static final Logger logger = LoggerFactory.getLogger(Factory.class);

    public Factory() throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/factory.properties"));

        int bodyCapacity =  Integer.parseInt(properties.getProperty("StorageBodyCapacity"));
        int motorCapacity =  Integer.parseInt(properties.getProperty("StorageMotorCapacity"));
        int accessoryCapacity =  Integer.parseInt(properties.getProperty("StorageAccessoryCapacity"));
        int autoCapacity =  Integer.parseInt(properties.getProperty("StorageAutoCapacity"));

        bodyStorage = new Storage<Body>(bodyCapacity);
        motorStorage = new Storage<Motor>(motorCapacity);
        accessoryStorage = new Storage<Accessory>(accessoryCapacity);
        autoStorage = new Storage<Auto>(autoCapacity);

        int numberOfAccessorySuppliers = Integer.parseInt(properties.getProperty("AccessorySuppliers"));
        int numberOfWorkers = Integer.parseInt(properties.getProperty("Workers"));

        workers = Executors.newFixedThreadPool(numberOfWorkers);

        AutoStorageController.Task task = new AutoStorageController.Task(bodyStorage, motorStorage, accessoryStorage, autoStorage);
        autoStorage.addObserver(new AutoStorageController(autoStorage, workers, task));

        suppliers.add(new Supplier<Body>(1000, bodyStorage, Body.class));
        suppliers.add(new Supplier<Motor>(1000, motorStorage, Motor.class));
        for (int i = 0; i < numberOfAccessorySuppliers; i++) {
            suppliers.add(new Supplier<Accessory>(1000, accessoryStorage, Accessory.class));
        }

        int numberOfDealers = Integer.parseInt(properties.getProperty("Dealers"));
        for (int i = 0; i < numberOfDealers; i++) {
            dealers.add(new Dealer(5000, autoStorage));
        }

    }

    public void start() {
        for (Supplier<? extends Detail> supplier : suppliers) {
            supplier.start();
        }
        for (Dealer dealer : dealers) {
            dealer.start();
        }
    }

    public void stop() {
        for (Supplier<? extends Detail> supplier : suppliers) {
            supplier.interrupt();
        }
        for (Dealer dealer : dealers) {
            dealer.interrupt();
        }
        workers.shutdown();
    }

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
