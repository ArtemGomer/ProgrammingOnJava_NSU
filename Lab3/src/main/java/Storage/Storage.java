package Storage;

import Observers.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Storage<T> extends Observable {
    private final ArrayList<T> details = new ArrayList<>();
    private final int CAPACITY;
    private static final Logger logger = LoggerFactory.getLogger(Storage.class);

    public Storage(int capacity) {
        CAPACITY = capacity;
    }

    public int getNumberOfDetails() {
        return this.details.size();
    }

    public int getCapacity() {
        return this.CAPACITY;
    }

    public synchronized T getDetail() throws InterruptedException {
        this.notifyObservers();
        while (details.size() == 0) {
            logger.info("Storage is empty. Waiting...");
            this.wait();
        }
        T detail = details.get(details.size() - 1);
        details.remove(detail);
        logger.info("Detail {} was got. Number of details : {}", detail.getClass().getSimpleName(), getNumberOfDetails());
        this.notify();
        return detail;
    }

    public synchronized void addDetail(T detail) throws InterruptedException {
        while (details.size() == this.CAPACITY) {
            logger.info("Storage of {} is full. Waiting...", detail.getClass().getSimpleName());
            this.wait();
        }
        details.add(detail);
        logger.info("New detail {} was added. Number of details {} : {}.", detail.getClass().getSimpleName(), detail.getClass().getSimpleName(), details.size());
        this.notify();
    }
}
