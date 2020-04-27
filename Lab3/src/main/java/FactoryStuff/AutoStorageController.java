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
import java.util.concurrent.ExecutorService;

public class AutoStorageController implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(AutoStorageController.class);

    private final Storage<Auto> storage;
    private final ExecutorService workers;
    private final Task task;

    public AutoStorageController(Storage<Auto> storage, ExecutorService workers, Task task) {
        this.storage = storage;
        this.workers = workers;
        this.task = task;
    }


    @Override
    public void update() {
        if ((double) storage.getNumberOfDetails() / storage.getCapacity() < 0.2) {
            logger.info("Create task to make some autos.");
            workers.execute(task);
        }
    }

    public static class Task implements Runnable{
        private final Storage<Body> bodyStorage;
        private final Storage<Motor> motorStorage;
        private final Storage<Accessory> accessoryStorage;
        private final Storage<Auto> autoStorage;

        public Task(Storage<Body> bodyStorage, Storage<Motor> motorStorage, Storage<Accessory> accessoryStorage, Storage<Auto> autoStorage) {
            this.bodyStorage = bodyStorage;
            this.motorStorage = motorStorage;
            this.accessoryStorage = accessoryStorage;
            this.autoStorage = autoStorage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 0.2 * autoStorage.getCapacity(); i++) {
                try {
                    logger.info("Trying to create auto.");
                    logger.info("Trying to get body.");
                    Body body = bodyStorage.getDetail();
                    logger.info("Trying to get motor.");
                    Motor motor = motorStorage.getDetail();
                    logger.info("Trying to get accessories.");
                    ArrayList<Accessory> accessories = new ArrayList<>();
                    for (int j = 0; j < 3; j++){
                        accessories.add(accessoryStorage.getDetail());
                    }
                    Auto auto = new Auto(body, motor, accessories);
                    logger.info("Trying to add auto.");
                    autoStorage.addDetail(auto);
                    logger.info("Auto was successfully created.");
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
