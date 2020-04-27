package FactoryStuff;

import Storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Supplier<T> extends Thread{
    private final int DELAY;
    private final Storage<T> storage;
    private final Class<T> detailType;

    private static final Logger logger = LoggerFactory.getLogger(Supplier.class);

    public Supplier(int delay, Storage<T> storage, Class<T> detailType) {
        this.DELAY = delay;
        this.storage = storage;
        this.detailType = detailType;
    }

    public void createDetail(Class<T> detailType) throws InterruptedException {
        try {
            T detail = detailType.getDeclaredConstructor().newInstance();
            logger.info("Successfully created detail {}.", detailType.getSimpleName());
            storage.addDetail(detail);
        } catch (InterruptedException ex){
            throw ex;
        } catch (Exception ex){
            logger.warn("Can not create detail {}.", detailType.getSimpleName());
        }
    }


    @Override
    public void run() {
        while(!this.isInterrupted()){
            try {
                logger.info("Trying to create detail {}.", detailType.getSimpleName());
                this.createDetail(detailType);
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
