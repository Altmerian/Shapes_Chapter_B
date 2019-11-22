package by.epam.pavelshakhlovich.shape.observer;

import by.epam.pavelshakhlovich.shape.datastorage.Event;
import by.epam.pavelshakhlovich.shape.entity.Shape;

public interface Observable {

    public void subscribe(Observer observer);

    public void unsubscribe(Observer observer);

    public void notifyObservers(Event event, int id, Shape shape);

}
