package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.action.TetrahedronCalculator;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;

public class SurfaceAreaWithinLimitsSpecification extends ShapeFilterSpecification {
    private double lowerLimit;
    private double upperLimit;

    public SurfaceAreaWithinLimitsSpecification(double lowerLimit, double upperLimit) {
        super("surface area from" + lowerLimit + " to " + upperLimit);
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean test(Shape shape) {
        if (shape instanceof Tetrahedron) {
            TetrahedronCalculator calculator = new TetrahedronCalculator((Tetrahedron) shape);
            double surfaceArea = calculator.calculateSurfaceArea();
            return lowerLimit <= surfaceArea && surfaceArea <= upperLimit;
        }
        else return false;
    }
}
