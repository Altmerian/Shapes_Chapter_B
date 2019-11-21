package by.epam.pavelshakhlovich.shape.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape implements Observable {
    private Point[] points;
    private List<Observer> observers = new ArrayList<>();

    public abstract void becomeChosen();

    @Override
    public void subscribe(Observer observer) {
        if(observer == null) {
            throw new NullPointerException("Null Observer");
        } else {
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

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public void notifyObservers(int id, Point[] points) {
        for (Observer observer : observers) {
            observer.update(id, points);
        }
    }

}
