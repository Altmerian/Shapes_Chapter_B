package by.epam.pavelshakhlovich.shape.datastorage;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.observer.Observer;
import by.epam.pavelshakhlovich.shape.observer.Observable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Repository implements Observable {
    private static int id;
    private Map<Integer, Shape> shapes = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();
    private static Repository instance;
    private static Logger logger = LogManager.getLogger();

    private Repository() {
    }

    public static Repository getInstance() {
        return instance == null ? instance = new Repository() : instance;
    }

    void add(Shape shape) {
        if (ShapeValidator.isValid(shape)) {
            shapes.put(++id, shape);
            notifyObservers(Event.ADD, id, shape);
            logger.info("shape was added to repository - \n{} ", shape);
        } else {
            logger.warn("shape is degenerate or its impossible to create {} from given points!",
                    shape.getRuntimeType());
        }
    }

    void update(int id, Point[] points) {
        Shape shape = shapes.get(id);
        shape.setPoints(points);
        if (ShapeValidator.isValid(shape)) {
            shapes.put(id, shape);
            notifyObservers(Event.UPDATE, id, shape);
            logger.info("shape (id {}) was updated - \n{} ", id, shape);
        } else {
            logger.warn("shape is degenerate or its impossible to create {} from given points!",
                    shape.getRuntimeType());
        }
    }

    void remove(int id) {
        if (shapes.containsKey(id)) {
            shapes.remove(id);
            notifyObservers(Event.REMOVE, id, null);
            logger.info("shape (id {}) was removed from repository", id);
        }
        logger.warn("There is no shape with id {}", id);
    }

    List<Shape> query(Object specification) {
        return null;
    }

    @Override
    public void subscribe(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Null Observer");
        } else {
            observers.add(observer);
        }
    }

    @Override
    public void unsubscribe(by.epam.pavelshakhlovich.shape.observer.Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Event event, int id, Shape shape) {
        for (Observer observer : observers) {
            observer.update(event, id, shape);
        }
    }

}
