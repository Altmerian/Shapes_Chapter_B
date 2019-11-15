package by.epam.pavelshakhlovich.shape.factory;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;

public class ShapesCreator {
    public Shape createShape(ShapeType type, Point[] points) {
        switch (type) {
            case TETRAHEDRON:
                return new Tetrahedron(points[0], points[1], points[2], points[3]);
            case UNKNOWN:
                return null;
            default:
                throw new EnumConstantNotPresentException(ShapeType.class, type.name());
        }
    }
}

