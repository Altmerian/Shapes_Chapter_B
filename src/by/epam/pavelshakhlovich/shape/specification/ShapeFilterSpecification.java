package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.entity.Shape;

import java.util.function.Predicate;

public abstract class ShapeFilterSpecification implements Predicate<Shape> {
    private String searchTerm;

    public ShapeFilterSpecification(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }
}
