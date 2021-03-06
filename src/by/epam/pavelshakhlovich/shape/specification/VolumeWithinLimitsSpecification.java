package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.action.TetrahedronCalculator;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;

public class VolumeWithinLimitsSpecification extends ShapeFilterSpecification {
    private double lowerLimit;
    private double upperLimit;

    public VolumeWithinLimitsSpecification(double lowerLimit, double upperLimit) {
        super("volume from" + lowerLimit + " to " + upperLimit);
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean test(Shape shape) {
        if (shape instanceof Tetrahedron) {
            TetrahedronCalculator calculator = new TetrahedronCalculator((Tetrahedron) shape);
            double volume = calculator.calculateVolume();
            return lowerLimit <= volume && volume <= upperLimit;
        }
        else return false;
    }
}
