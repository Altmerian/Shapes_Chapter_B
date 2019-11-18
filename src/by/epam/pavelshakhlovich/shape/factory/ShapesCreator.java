package by.epam.pavelshakhlovich.shape.factory;

import by.epam.pavelshakhlovich.shape.entity.*;
import org.jetbrains.annotations.NotNull;

public class ShapesCreator {

    @NotNull
    public Shape createShape(ShapeType type, Point[] points) {
        switch (type) {
            case TETRAHEDRON:
                return new Tetrahedron(points);
            case UNKNOWN:
                return new UnknownShape(points);
            default:
                throw new EnumConstantNotPresentException(ShapeType.class, type.name());
        }
    }
}

