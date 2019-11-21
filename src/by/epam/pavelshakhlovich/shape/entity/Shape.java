package by.epam.pavelshakhlovich.shape.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape implements Observable {
    public List<Observer> observers = new ArrayList<>();

    public abstract void becomeChosen();

    @Override
    public void subscribe(Observer observer) {
        if(observer == null) {
            throw new NullPointerException("Null Observer");
        } else if(!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
