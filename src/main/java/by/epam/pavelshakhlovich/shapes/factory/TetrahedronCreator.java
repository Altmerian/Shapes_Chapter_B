package by.epam.pavelshakhlovich.shapes.factory;

import by.epam.pavelshakhlovich.shapes.entity.Point;
import by.epam.pavelshakhlovich.shapes.entity.Tetrahedron;

public class TetrahedronCreator extends Creator {
    protected Tetrahedron createShape(Point [] points) {
        return new Tetrahedron(points[0], points[1], points[2], points[3]);
    }
}
