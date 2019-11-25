package by.epam.pavelshakhlovich.shape.comporator;

import by.epam.pavelshakhlovich.shape.entity.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShapeChainedComparator extends ShapeComparator {
    private List<ShapeComparator> shapeComparators = new ArrayList<>();

    public ShapeChainedComparator(ShapeComparator... shapeComparators) {
        super(Arrays.stream(shapeComparators)
                .map(ShapeComparator::getName)
                .collect(Collectors.joining(" ")));
        this.shapeComparators = Arrays.asList(shapeComparators);
    }

    @Override
    public int compare(Shape shape1, Shape shape2) {
        for (ShapeComparator shapeComparator : shapeComparators) {
            int result = shapeComparator.compare(shape1, shape2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
