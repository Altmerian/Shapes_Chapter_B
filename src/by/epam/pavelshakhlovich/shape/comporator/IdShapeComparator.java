package by.epam.pavelshakhlovich.shape.comporator;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class IdShapeComparator extends ShapeComparator {

    public IdShapeComparator() {
        super("`ID`");
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        int id1 = shape1.getId();
        int id2 = shape2.getId();
        return Integer.compare(id1, id2);
    }
}
