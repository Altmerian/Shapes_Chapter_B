package by.epam.pavelshakhlovich.shape.comporator;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class FirstPointZCoordinateShapeComparator extends ShapeComparator {

    public FirstPointZCoordinateShapeComparator() {
        super("`First point Z coordinate`");
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        double z1 = shape1.getPoints()[0].getZ();
        double z2 = shape2.getPoints()[0].getZ();
        return Double.compare(z1, z2);
    }
}
