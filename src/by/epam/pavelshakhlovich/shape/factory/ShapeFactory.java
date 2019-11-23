package by.epam.pavelshakhlovich.shape.factory;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.entity.UnknownShape;
import by.epam.pavelshakhlovich.shape.inputdata.DataParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class ShapeFactory {
    private static Logger logger = LogManager.getLogger();

    /**
     * Creates concrete Shape object depends on type
     *
     * @param type   valid parsed data by {@link DataParser}
     * @param points valid parsed data by {@link DataParser}
     * @return @NotNull
     */
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

