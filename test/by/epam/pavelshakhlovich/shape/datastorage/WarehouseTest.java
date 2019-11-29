package by.epam.pavelshakhlovich.shape.datastorage;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.exception.ShapeDataNotFoundException;
import by.epam.pavelshakhlovich.shape.observer.RepositoryWatcher;
import by.epam.pavelshakhlovich.shape.specification.AddingAnyShapesSpecification;
import by.epam.pavelshakhlovich.shape.specification.AddingUniqueShapesSpecification;
import by.epam.pavelshakhlovich.shape.specification.PointsEqualsSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WarehouseTest {
    private static Tetrahedron tetrahedron;
    private static Point[] points;
    private static Repository repository = new Repository();
    private static Warehouse warehouse = new Warehouse();


    static {
        Point point1 = new Point(2.0, 0.0, 1.0);
        Point point2 = new Point(5.0, 6.0, 1.0);
        Point point3 = new Point(10.0, 9.0, 7.0);
        Point point4 = new Point(3.0, 5.0, 1.0);
        points = new Point[]{point1, point2, point3, point4};
        tetrahedron = new Tetrahedron(points);
        repository.subscribe(new RepositoryWatcher().attachToWarehouse(warehouse));

    }

    @Test (expectedExceptions = ShapeDataNotFoundException.class)
    public void testAddAndRemoveNotification() {
        repository.add(new AddingUniqueShapesSpecification(), tetrahedron);
        repository.remove(new PointsEqualsSpecification(points));
        assertNull(warehouse.getShapeDataById(1));
    }

    @Test (dependsOnMethods = "testAddAndRemoveNotification")
    public void testUpdateNotification() {
        Tetrahedron tetrahedron = new Tetrahedron(points);
        repository.add(new AddingUniqueShapesSpecification(), tetrahedron);
        int expectedId = tetrahedron.getId();
        Point[] newPoints = new Point[]{
                new Point(2.0, 5.0, 2.0),
                new Point(5.0, 6.5, 4.0),
                new Point(1.0, 5.0, 4.0),
                new Point(6.0, 5.0, 4.0)};
        repository.update(tetrahedron, newPoints);
        Warehouse.ShapeData actualData = warehouse.getShapeDataById(expectedId);

        Tetrahedron updatedTetrahedron = new Tetrahedron(newPoints);
        Warehouse.ShapeData expectedData = new Warehouse.ShapeData(updatedTetrahedron);
        Double[] values = warehouse.calculateData(updatedTetrahedron);
        expectedData.setId(expectedId);
        expectedData.setSurfaceArea(values[0]);
        expectedData.setVolume(values[1]);

        assertEquals(actualData, expectedData);
    }

}