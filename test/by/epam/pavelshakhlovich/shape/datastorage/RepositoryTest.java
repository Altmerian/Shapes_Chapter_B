package by.epam.pavelshakhlovich.shape.datastorage;

import by.epam.pavelshakhlovich.shape.comporator.FirstPointXCoordinateShapeComparator;
import by.epam.pavelshakhlovich.shape.comporator.FirstPointYCoordinateShapeComparator;
import by.epam.pavelshakhlovich.shape.comporator.ShapeChainedComparator;
import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.specification.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;


public class RepositoryTest {
    private static Tetrahedron tetrahedron;
    private static Point[] points;
    private static Repository repository;

    static {
        Point point1 = new Point(2.0, 0.0, 1.0);
        Point point2 = new Point(5.0, 6.0, 1.0);
        Point point3 = new Point(10.0, 9.0, 7.0);
        Point point4 = new Point(3.0, 5.0, 1.0);
        points = new Point[]{point1, point2, point3, point4};
    }

    @BeforeMethod(onlyForGroups = {"second"})
    public static void setUp() {
        tetrahedron = new Tetrahedron(points);
        repository = new Repository();
        repository.add(new AddingAnyShapesSpecification(), tetrahedron);
    }


    @Test(groups = {"first"})
    public void testAdd() {
        Repository repository = new Repository();
        Point[] points = new Point[]{
                new Point(2.0, 5.0, 2.0),
                new Point(5.0, 6.5, 4.0),
                new Point(1.0, 5.0, 4.0),
                new Point(6.0, 5.0, 4.0)};
        List<Shape> expectedShapeList = List.of(new Tetrahedron(points));
        List<Shape> actualShapeList = repository.add(new AddingUniqueShapesSpecification(), new Tetrahedron(points));
        assertEquals(actualShapeList, expectedShapeList);
    }

    @Test(groups = {"second"})
    public void testUpdate() {
        Point[] newPoints = new Point[]{
                new Point(3.0, 2.0, 4.0),
                new Point(5.0, 6.5, 4.0),
                new Point(1.0, -5.0, 63.0),
                new Point(6.0, 5.0, 4.0)
        };
        Shape expectedShape = new Tetrahedron(newPoints);
        Shape actualShape = repository.update(tetrahedron, newPoints);
        assertEquals(actualShape, expectedShape);
    }

    @Test(groups = {"second"})
    public void testUpdateWithId() {
        Point[] newPoints = new Point[]{
                new Point(5.0, 2.0, 4.0),
                new Point(1.0, 3.5, 4.0),
                new Point(-1.0, -5.0, 3.0),
                new Point(.0, 5.0, 4.0)
        };
        Shape expectedShape = new Tetrahedron(newPoints);
        int index = repository.query(new PointsEqualsSpecification(points))
                .get(0).getId();
        Shape actualShape = repository.update(index, newPoints);
        assertEquals(actualShape, expectedShape);
    }

    @Test(groups = {"second"})
    public void testQueryByName() {
        Shape expectedShape = tetrahedron;
        List<Shape> expected = List.of(expectedShape);
        NameEqualsSpecification shapeSpecification = new NameEqualsSpecification("Tetrahedron");
        List<Shape> actual = repository.query(shapeSpecification);
        assertEquals(actual, expected);
    }


    @Test(groups = {"second"})
    public void testRemove() {
        PointsEqualsSpecification specification = new PointsEqualsSpecification(points);
        List<Shape> expected = List.of(tetrahedron);
        List<Shape> actual = repository.remove(specification);
        assertEquals(actual, expected);
    }

    @Test(groups = {"third"})
    public void testSort() {
        Shape tetrahedron1 = new Tetrahedron(new Point[]{
                new Point(3.0, 2.0, 4.0),
                new Point(5.0, 6.5, 4.0),
                new Point(1.0, -5.0, 63.0),
                new Point(6.0, 5.0, 4.0)
        });
        Shape tetrahedron2 = new Tetrahedron(new Point[]{
                new Point(3.0, 4.0, 62.0),
                new Point(5.0, 6.5, 4.0),
                new Point(1.0, -5.0, 4.0),
                new Point(6.0, 5.0, 4.0)
        });
        Shape tetrahedron3 = new Tetrahedron(new Point[]{
                new Point(2.0, 8.0, 0.0),
                new Point(1.0, 3.5, 3.0),
                new Point(-1.0, -5.0, 3.0),
                new Point(0.0, 5.0, 3.0)
        });
        List<Shape> shapeList = List.of(tetrahedron1, tetrahedron2, tetrahedron3);
        Repository repository = new Repository();
        repository.add(new AddingUniqueShapesSpecification(), shapeList.toArray(new Shape[0]));
        FirstPointXCoordinateShapeComparator shapeComparatorX = new FirstPointXCoordinateShapeComparator();
        FirstPointYCoordinateShapeComparator shapeComparatorY = new FirstPointYCoordinateShapeComparator();
        ShapeChainedComparator chainedComparator = new ShapeChainedComparator(shapeComparatorX, shapeComparatorY);
        List<Shape> shapesToSort = repository.query(Repository.ALL_SHAPES_INCLUDED_SPECIFICATION);
        List<Shape> actual = repository.sort(shapesToSort, chainedComparator);
        List<Shape> expected = List.of(tetrahedron3, tetrahedron1, tetrahedron2);
         assertEquals(actual, expected);
    }

}