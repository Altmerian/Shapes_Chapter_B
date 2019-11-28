package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

public class AddingAnyShapesSpecification implements AddingShapesSpecification {

    @Override
    public Shape[] apply(Shape... incomingShapes) {
        return incomingShapes;
    }
}
