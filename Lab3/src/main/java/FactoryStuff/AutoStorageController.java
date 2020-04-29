package FactoryStuff;

import Details.Accessory;
import Details.Auto;
import Details.Body;
import Details.Motor;
import Observers.Observer;
import Storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

public class AutoStorageController implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(AutoStorageController.class);

    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Auto> autoStorage;
    private final ThreadPoolExecutor workers;

    public AutoStorageController(Storage<Body> bodyStorage, Storage<Motor> motorStorage,
                                 Storage<Accessory> accessoryStorage, Storage<Auto> autoStorage, ThreadPoolExecutor workers) {
        this.bodyStorage = bodyStorage;
        this.motorStorage = motorStorage;
        this.accessoryStorage = accessoryStorage;
        this.autoStorage = autoStorage;
        this.workers = workers;
    }


    @Override
    public void update() {
        long carsInProgress = workers.getTaskCount() - workers.getCompletedTaskCount();
        if ((double) (autoStorage.getNumberOfDetails() + carsInProgress) / autoStorage.getCapacity() < 0.2) {
            logger.info("Create tasks to make some autos.");
            for (int i = 0; i < 0.2 * autoStorage.getCapacity() + 1; i++){
                workers.execute(new Task());
            }
        }
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            logger.info("Trying to create auto.");
            try {
                logger.info("Trying to get body.");
                Body body = bodyStorage.getDetail();
                logger.info("Trying to get motor.");
                Motor motor = motorStorage.getDetail();
                logger.info("Trying to get accessories.");
                ArrayList<Accessory> accessories = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    accessories.add(accessoryStorage.getDetail());
                }
                Auto auto = new Auto(body, motor, accessories);
                logger.info("Trying to add auto.");
                autoStorage.addDetail(auto);
            } catch (InterruptedException ex){
                logger.warn("Thread was interrupted", ex);
            }
            logger.info("Auto was successfully created.");
        }
    }
}
