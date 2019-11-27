package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class AllShapesIncludedSpecification implements ShapeFilterSpecification {

    @Override
    public boolean test(Shape shape) {
        return true;
    }
}
