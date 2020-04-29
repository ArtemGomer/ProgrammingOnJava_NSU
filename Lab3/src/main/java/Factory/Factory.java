package Factory;

import Details.*;
import FactoryStuff.AutoStorageController;
import FactoryStuff.Dealer;
import FactoryStuff.Supplier;
import Storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Factory {
    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Auto> autoStorage;

    private final ArrayList<Supplier<? extends Detail>> suppliers = new ArrayList<>();
    private final ArrayList<Dealer> dealers = new ArrayList<>();
    private final ThreadPoolExecutor workers;

    public Factory() throws IOException {

        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/factory.properties"));

        int bodyCapacity =  Integer.parseInt(properties.getProperty("StorageBodyCapacity"));
        int motorCapacity =  Integer.parseInt(properties.getProperty("StorageMotorCapacity"));
        int accessoryCapacity =  Integer.parseInt(properties.getProperty("StorageAccessoryCapacity"));
        int autoCapacity =  Integer.parseInt(properties.getProperty("StorageAutoCapacity"));

        bodyStorage = new Storage<>(bodyCapacity, Body.class);
        motorStorage = new Storage<>(motorCapacity, Motor.class);
        accessoryStorage = new Storage<>(accessoryCapacity, Accessory.class);
        autoStorage = new Storage<>(autoCapacity, Auto.class);

        int numberOfAccessorySuppliers = Integer.parseInt(properties.getProperty("AccessorySuppliers"));
        int numberOfWorkers = Integer.parseInt(properties.getProperty("Workers"));

        workers = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfWorkers);

        autoStorage.addObserver(new AutoStorageController(bodyStorage, motorStorage, accessoryStorage,autoStorage, workers));

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
}
