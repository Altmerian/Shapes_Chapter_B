package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.factory.ShapeType;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class ShapeAddingSpecification implements BiFunction<Integer, ShapeType, Shape[]> {

    @Override
    public Shape[] apply(Integer integer, ShapeType shapeType) {

        return new Shape[integer];
    }
}
