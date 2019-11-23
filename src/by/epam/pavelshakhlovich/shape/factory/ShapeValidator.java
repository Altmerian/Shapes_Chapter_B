package by.epam.pavelshakhlovich.shape.factory;

import by.epam.pavelshakhlovich.shape.action.TetrahedronAction;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;

public class ShapeValidator {
    public static boolean isValid(Shape shape) {
        if (shape.getClass() == Tetrahedron.class) {
            TetrahedronAction action = new TetrahedronAction((Tetrahedron) shape);
            return !action.isDegenerate();
        } else {
            return false;
        }
    }
}
