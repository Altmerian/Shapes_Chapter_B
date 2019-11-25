package by.epam.pavelshakhlovich.shape.comporator;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class FirstPointYCoordinateShapeComparator extends ShapeComparator {

    public FirstPointYCoordinateShapeComparator() {
        super("`First point Y coordinate`");
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        double y1 = shape1.getPoints()[0].getY();
        double y2 = shape2.getPoints()[0].getY();
        return Double.compare(y1, y2);
    }
}
