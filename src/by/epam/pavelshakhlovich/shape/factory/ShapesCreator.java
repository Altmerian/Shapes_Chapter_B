package by.epam.pavelshakhlovich.shape.factory;

import by.epam.pavelshakhlovich.shape.entity.*;
import org.jetbrains.annotations.NotNull;

public class ShapesCreator {

    @NotNull
    public Shape createShape(ShapeType type, Point[] points) {
        switch (type) {
            case TETRAHEDRON:
                return new Tetrahedron(points[0], points[1], points[2], points[3]);
            case UNKNOWN:
                return new UnknownShape(points[0], points[1]);
            default:
                throw new IncorrectShapeType("Incorrect shape description: " + type.name());
        }
    }
}

