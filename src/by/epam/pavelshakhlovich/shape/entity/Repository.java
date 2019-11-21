package by.epam.pavelshakhlovich.shape.entity;

import java.util.*;

public class Repository{
    private static int id;
    private Map<Integer, Shape> shapes = new HashMap<>();

    void add(Shape shape) {
        shapes.put(++id, shape);
        shape.subscribe(Warehouse.getInstance());
    }

    void update(int id, Point[] points) {
        Shape shape = shapes.get(id);
        shape.setPoints(points);
        shape.notifyObservers(id, points);
    }

    void remove(int id) {
        shapes.remove(id);
    }

    List <Shape> query(Object specification) {
       return null;
    }
}
