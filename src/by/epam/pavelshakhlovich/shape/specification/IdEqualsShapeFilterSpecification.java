package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class IdEqualsShapeFilterSpecification implements ShapeFilterSpecification {
    private int idToMatch;

    public IdEqualsShapeFilterSpecification(int idToMatch) {
        this.idToMatch = idToMatch;
    }

    @Override
    public boolean test(Shape shape) {
        return shape.getId() == idToMatch;
    }
}
