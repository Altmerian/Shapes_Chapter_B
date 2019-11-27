package by.epam.pavelshakhlovich.shape.datastorage;

import by.epam.pavelshakhlovich.shape.comporator.FirstPointXCoordinateShapeComparator;
import by.epam.pavelshakhlovich.shape.comporator.FirstPointYCoordinateShapeComparator;
import by.epam.pavelshakhlovich.shape.comporator.ShapeChainedComparator;
import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.specification.AddingShapesSpecification;
import by.epam.pavelshakhlovich.shape.specification.IdEqualsShapeSpecification;
import by.epam.pavelshakhlovich.shape.specification.NameEqualsSpecification;
import by.epam.pavelshakhlovich.shape.specification.PointsEqualsSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class RepositoryTest {
    private static Point[] points;

    static {
        Point point1 = new Point(2.0, 0.0, 1.0);
        Point point2 = new Point(5.0, 6.0, 1.0);
        Point point3 = new Point(10.0, 9.0, 7.0);
        Point point4 = new Point(3.0, 5.0, 1.0);
        points = new Point[]{point1, point2, point3, point4};
    }

    @Test(groups = {"first"})
    public void testAdd() {
        Shape expectedShape = new Tetrahedron(points);
        List<Shape> expectedShapeList = List.of(expectedShape);
        List<Shape> actualShapeList = new ArrayList<>();
        if (Repository.getInstance().contains(expectedShape)) {
            expectedShapeList = Collections.emptyList();
        } else {
            actualShapeList = Repository.getInstance()
                    .add(new AddingShapesSpecification(), new Tetrahedron(points));
        }
        assertEquals(actualShapeList, expectedShapeList);
    }

    @Test(groups = {"first"})
    public void testUpdate() {
        Shape tetrahedron = new Tetrahedron(points);
        Repository.getInstance().add(new AddingShapesSpecification(), tetrahedron);
        Point[] newPoints = new Point[]{
                new Point(3.0, 2.0, 4.0),
                new Point(5.0, 6.5, 4.0),
                new Point(1.0, -5.0, 63.0),
                new Point(6.0, 5.0, 4.0)
        };
        Shape expectedShape = new Tetrahedron(newPoints);
        Shape actualShape = Repository.getInstance().update(tetrahedron, newPoints);
        assertEquals(actualShape, expectedShape);
    }

    @Test(groups = {"second"})
    public void testUpdateWithId() {
        Shape tetrahedron = new Tetrahedron(points);
        Repository.getInstance().add(new AddingShapesSpecification(), tetrahedron);
        Point[] newPoints = new Point[]{
                new Point(5.0, 2.0, 4.0),
                new Point(1.0, 3.5, 4.0),
                new Point(-1.0, -5.0, 3.0),
                new Point(.0, 5.0, 4.0)
        };
        Shape expectedShape = new Tetrahedron(newPoints);
        int index = Repository.getInstance()
                .query(new PointsEqualsSpecification(points))
                .get(0).getId();
        Shape actualShape = Repository.getInstance().update(index, newPoints);
        assertEquals(actualShape, expectedShape);
    }

    @Test(groups = {"second"})
    public void testQuery() {
        Shape expectedShape = new Tetrahedron(points);
        List<Shape> expected = List.of(expectedShape);
        Repository.getInstance().add(new AddingShapesSpecification(), expectedShape);
        NameEqualsSpecification shapeSpecification = new NameEqualsSpecification("Tetrahedron");
        List<Shape> actual = Repository.getInstance().query(shapeSpecification);
        assertEquals(actual, expected);
    }

    @Test(groups = {"third"})
    public void testRemove() {
        Shape tetrahedron = new Tetrahedron(points);
        Repository.getInstance().add(new AddingShapesSpecification(), tetrahedron);
        PointsEqualsSpecification specification = new PointsEqualsSpecification(points);
        List<Shape> expected = List.of(tetrahedron);
        List<Shape> actual = Repository.getInstance().remove(specification);
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
        Repository.getInstance().add(new AddingShapesSpecification(), shapeList.toArray(new Shape[0]));
        FirstPointXCoordinateShapeComparator shapeComparatorX = new FirstPointXCoordinateShapeComparator();
        FirstPointYCoordinateShapeComparator shapeComparatorY = new FirstPointYCoordinateShapeComparator();
        ShapeChainedComparator chainedComparator = new ShapeChainedComparator(shapeComparatorX, shapeComparatorY);
        List<Shape> expected = List.of(tetrahedron3, tetrahedron1, tetrahedron2);
        Repository.getInstance().sort(chainedComparator);
        List<Shape> actual = Repository.getInstance()
                .query(Repository.ALL_SHAPES_INCLUDED_SPECIFICATION);
        assertEquals(actual, expected);
    }

}