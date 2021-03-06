package by.epam.pavelshakhlovich.shape.observer;

import by.epam.pavelshakhlovich.shape.datastorage.Event;
import by.epam.pavelshakhlovich.shape.datastorage.Warehouse;
import by.epam.pavelshakhlovich.shape.entity.Shape;

import java.util.ArrayList;
import java.util.List;

public class RepositoryWatcher implements Observer {
    private final List<Warehouse> warehouses = new ArrayList<>();

    @Override
    public void notify(Event event, Shape shape) {
        for (Warehouse warehouse : warehouses) {
            warehouse.receiveNotification(event, shape);
        }
    }

    public RepositoryWatcher attachToWarehouse (Warehouse warehouse) {
        warehouses.add(warehouse);
        return this;
    }

}
