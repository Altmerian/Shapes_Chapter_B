package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class IdEqualsShapeSpecification extends ShapeFilterSpecification {
    private int idToMatch;

    public IdEqualsShapeSpecification(int idToMatch) {
        super("ID = " + idToMatch);
        this.idToMatch = idToMatch;
    }

    @Override
    public boolean test(Shape shape) {
        return shape.getId() == idToMatch;
    }
}
