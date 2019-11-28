package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;

import java.util.Arrays;

public class PointsEqualsSpecification extends ShapeFilterSpecification {
    private Point[] points;

    public PointsEqualsSpecification(Point[] points) {
        super("points = " + Arrays.toString(points));
        this.points = points;
    }

    @Override
    public boolean test(Shape shape) {
        return Arrays.deepEquals(shape.getPoints(), points);
    }
}
