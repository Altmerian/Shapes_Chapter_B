package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class NameEqualsSpecification extends ShapeFilterSpecification {
    private String shapeName;

    public NameEqualsSpecification(String shapeName) {
        super("shape name = " + shapeName );
        this.shapeName = shapeName;
    }

    @Override
    public boolean test(Shape shape) {
        return shape.getClass().getSimpleName().equalsIgnoreCase(shapeName);
    }
}
