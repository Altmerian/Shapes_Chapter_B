package by.epam.pavelshakhlovich.shape.factory;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapeFactoryTest {

    @Test
    public void testCreateShape() {
        Point point1 = new Point(2.0, 0.0, 1.0);
        Point point2 = new Point(5.0, 6.0, 1.0);
        Point point3 = new Point(10.0, 9.0, 7.0);
        Point point4 = new Point(3.0, 5.0, 1.0);
        Point[] points = new Point[] {point1, point2, point3, point4};
        Shape expected = new Tetrahedron(points);
        ShapeFactory factory = new ShapeFactory();
        Shape actual = factory.createShape(ShapeType.TETRAHEDRON, points);
        Assert.assertEquals(expected, actual);
    }
}