package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class AllShapesIncludedSpecification extends ShapeFilterSpecification {

    public AllShapesIncludedSpecification() {
        super("all shapes");
    }

    @Override
    public boolean test(Shape shape) {
        return true;
    }
}
