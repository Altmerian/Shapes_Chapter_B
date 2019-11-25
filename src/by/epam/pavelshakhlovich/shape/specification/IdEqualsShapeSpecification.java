package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class IdEqualsShapeSpecification implements ShapeSpecification {
    private int idToMatch;

    public IdEqualsShapeSpecification(int idToMatch) {
        this.idToMatch = idToMatch;
    }

    @Override
    public boolean test(Shape shape) {
        return shape.getId() == idToMatch;
    }
}
