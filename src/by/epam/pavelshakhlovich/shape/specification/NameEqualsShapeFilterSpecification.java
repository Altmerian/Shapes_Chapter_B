package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class NameEqualsShapeFilterSpecification implements ShapeFilterSpecification {
    private String shapeName;

    public NameEqualsShapeFilterSpecification(String shapeName) {
        this.shapeName = shapeName;
    }

    @Override
    public boolean test(Shape shape) {
        return shape.getClass().getSimpleName().equalsIgnoreCase(shapeName);
    }
}
