package FactoryStuff;

import Details.Auto;

import Storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dealer extends Thread{
    private final int DELAY;
    private final Storage<Auto> autoStorage;
    private static final Logger logger = LoggerFactory.getLogger(Dealer.class);

    public Dealer(int delay, Storage<Auto> storage){
        this.autoStorage = storage;
        this.DELAY = delay;
    }

    @Override
    public void run() {
        while(!this.isInterrupted()){
            try {
                logger.info("Trying to buy auto.");
                autoStorage.getDetail();
                logger.info("Auto was bought. Number of autos : {}", autoStorage.getNumberOfDetails());
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
