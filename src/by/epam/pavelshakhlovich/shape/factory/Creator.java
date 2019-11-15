package by.epam.pavelshakhlovich.shape.factory;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;

public abstract class Creator {
    public abstract Shape createShape (Point ... points);
}
