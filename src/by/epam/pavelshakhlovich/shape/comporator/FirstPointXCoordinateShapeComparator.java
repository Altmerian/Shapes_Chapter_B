package by.epam.pavelshakhlovich.shape.comporator;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class FirstPointXCoordinateShapeComparator extends ShapeComparator {

    public FirstPointXCoordinateShapeComparator() {
        super("`First point X coordinate`");
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        double x1 = shape1.getPoints()[0].getX();
        double x2 = shape2.getPoints()[0].getX();
        return Double.compare(x1, x2);
    }
}
