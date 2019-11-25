package by.epam.pavelshakhlovich.shape.comporator;

import by.epam.pavelshakhlovich.shape.entity.Shape;

import java.util.Comparator;

public abstract class ShapeComparator implements Comparator<Shape> {
    protected String name;

    public ShapeComparator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
