package by.epam.pavelshakhlovich.shapes.factory;

import by.epam.pavelshakhlovich.shapes.entity.Point;
import by.epam.pavelshakhlovich.shapes.entity.Shape;

public abstract class Creator {
    protected abstract Shape createShape (Point ... points);
}
