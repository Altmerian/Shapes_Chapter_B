package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class NameEqualsShapeSpecification implements ShapeSpecification {
    private String shapeName;

    public NameEqualsShapeSpecification(String shapeName) {
        this.shapeName = shapeName;
    }

    @Override
    public boolean test(Shape shape) {
        return shape.getClass().getSimpleName().equalsIgnoreCase(shapeName);
    }
}
