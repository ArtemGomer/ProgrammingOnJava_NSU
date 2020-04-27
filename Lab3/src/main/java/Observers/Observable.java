package Observers;

import java.util.ArrayList;

public class Observable {
    private final ArrayList<Observer> observers = new ArrayList<>();
    public void notifyObservers(){
        for (Observer observer : observers){
            observer.update();
        }
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }
}
