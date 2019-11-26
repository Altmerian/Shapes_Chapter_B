package by.epam.pavelshakhlovich.shape.datastorage;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.specification.AddingShapesSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class RepositoryTest {
    private static Point[] points;

    static {
        Point point1 = new Point(2.0, 0.0, 1.0);
        Point point2 = new Point(5.0, 6.0, 1.0);
        Point point3 = new Point(10.0, 9.0, 7.0);
        Point point4 = new Point(3.0, 5.0, 1.0);
        points = new Point[] {point1, point2, point3, point4};
    }



    @Test
    public void testAdd() {
        Shape expectedShape = new Tetrahedron(points);
        List<Shape> actualShapeList = Repository.getInstance()
                .add(new AddingShapesSpecification(), new Tetrahedron(points));
        Shape actualShape = actualShapeList.get(0);
        Assert.assertEquals(actualShape, expectedShape);
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testQuery() {
    }

    @Test
    public void testSort() {
    }
}