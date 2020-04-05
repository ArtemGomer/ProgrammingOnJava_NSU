package Observers;

import java.util.ArrayList;

public interface Observable {
    ArrayList<Observer> observers = new ArrayList<>();
    default void notifyObservers(){
        for (Observer observer : observers){
            observer.update();
        }
    }

    default void addObserver(Observer observer){
        observers.add(observer);
    }
}
