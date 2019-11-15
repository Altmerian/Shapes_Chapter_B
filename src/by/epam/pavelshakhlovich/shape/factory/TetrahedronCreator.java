package by.epam.pavelshakhlovich.shape.factory;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;

public class TetrahedronCreator extends Creator {
    public Shape createShape(Point[] points) {
        return new Tetrahedron(points[0], points[1], points[2], points[3]);
    }
}
