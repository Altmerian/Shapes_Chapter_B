package by.epam.pavelshakhlovich.shape.observer;

import by.epam.pavelshakhlovich.shape.datastorage.Event;
import by.epam.pavelshakhlovich.shape.entity.Shape;

public interface Observer {

    void update(Event event, int id, Shape shape);
}
