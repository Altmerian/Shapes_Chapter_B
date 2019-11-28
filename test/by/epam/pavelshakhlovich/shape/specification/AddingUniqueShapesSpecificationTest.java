package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.datastorage.Repository;
import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AddingUniqueShapesSpecificationTest {

    private static final AddingUniqueShapesSpecification specification = new AddingUniqueShapesSpecification();
    private static Tetrahedron tetrahedron;

    static {
        Point point1 = new Point(2.0, 0.0, 1.0);
        Point point2 = new Point(5.0, 6.0, 1.0);
        Point point3 = new Point(10.0, 9.0, 7.0);
        Point point4 = new Point(3.0, 5.0, 1.0);
        tetrahedron = new Tetrahedron(new Point[] {point1, point2, point3, point4});
    }

    @Test
    public void testApply() {
        Shape expectedShape = tetrahedron;
        Shape[] actualShapeArray = specification.apply(tetrahedron);
        Shape actualShape = actualShapeArray[0];
        assertEquals(actualShape, expectedShape);
    }

    @Test
    public void testApplyWithExistenceShape() {
        Repository.getInstance().add(specification, tetrahedron);
        Shape[] actualShapeArray = specification.apply(tetrahedron);
        Shape actualShape = actualShapeArray[0];
        assertNull(actualShape);
    }

}