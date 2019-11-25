package by.epam.pavelshakhlovich.shape.comporator;

import by.epam.pavelshakhlovich.shape.entity.Shape;

import java.util.Comparator;

public class ShapeComparator implements Comparator<Shape> {
    private ComparingBy comparingType;

    public ShapeComparator(ComparingBy comparingType) {
        this.comparingType = comparingType;
    }

    public ComparingBy getComparingType() {
        return comparingType;
    }

    public void setComparingType(ComparingBy comparingType) {
        this.comparingType = comparingType;
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        switch (comparingType) {
            case ID: {
                int id1 = shape1.getId();
                int id2 = shape2.getId();
                return Integer.compare(id1, id2);
            }
            case NAME: {
                String name1 = shape1.getClass().getSimpleName();
                String name2 = shape2.getClass().getSimpleName();
                return name1.compareTo(name2);
            }
            case FIRST_POINT_X_COORDINATE: {
                double x1 = shape1.getPoints()[0].getX();
                double x2 = shape2.getPoints()[0].getX();
                return Double.compare(x1, x2);
            }
            case FIRST_POINT_Y_COORDINATE: {
                double y1 = shape1.getPoints()[0].getY();
                double y2 = shape2.getPoints()[0].getY();
                return Double.compare(y1, y2);
            }
            case FIRST_POINT_Z_COORDINATE: {
                double z1 = shape1.getPoints()[0].getZ();
                double z2 = shape2.getPoints()[0].getZ();
                return Double.compare(z1, z2);
            }
            default:
                throw new EnumConstantNotPresentException(ComparingBy.class, comparingType.name());
        }
    }
}
