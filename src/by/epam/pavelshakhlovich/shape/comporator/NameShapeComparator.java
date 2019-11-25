package by.epam.pavelshakhlovich.shape.comporator;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class NameShapeComparator extends ShapeComparator {

    public NameShapeComparator() {
        super("`Name`");
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        String name1 = shape1.getClass().getSimpleName();
        String name2 = shape2.getClass().getSimpleName();
        return name1.compareTo(name2);
    }
}
