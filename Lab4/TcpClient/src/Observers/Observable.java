package Observers;

import Messages.Message;

import java.util.ArrayList;

public class Observable {
    protected Message message;
    private final ArrayList<Observer> observers = new ArrayList<>();
    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update(this.message);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
}
