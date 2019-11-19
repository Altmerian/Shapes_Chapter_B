package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class TetrahedronActionTest {

    @Test
    public void testIsDegenerate() {
        Point point1 = new Point(-1.0, -3.0, -3.0);
        Point point2 = new Point(5.0, -3.0, 0.0);
        Point point3 = new Point(-1.0, -3.0, -3.0);
        Point point4 = new Point(2.0, 4.0, 1.0);
        Tetrahedron tetrahedron = new Tetrahedron(point1, point2, point3, point4);
        TetrahedronAction action = new TetrahedronAction(tetrahedron);
        assertTrue(action.isDegenerate());
    }

    @Test
    public void testLieOnCoordinatePlane() {
        Point point1 = new Point(-1.0, -0.0, -3.0);
        Point point2 = new Point(5.0, 0.0, 0.0);
        Point point3 = new Point(-4.0, -0.0, 2.0);
        Point point4 = new Point(2.0, 4.0, 1.0);
        Tetrahedron tetrahedron = new Tetrahedron(point1, point2, point3, point4);
        TetrahedronAction action = new TetrahedronAction(tetrahedron);
        List<String> expected = new ArrayList<>();
        expected.add("XZ");
        assertEquals(action.lieOnCoordinatePlane(), expected);
    }

    @Test
    public void testFindIntersectionFactors() {
        Point point1 = new Point(-1.0, -3.0, -3.0);
        Point point2 = new Point(5.0, -3.0, 0.0);
        Point point3 = new Point(-4.0, -3.0, 2.0);
        Point point4 = new Point(2.0, 4.0, 1.0);
        Tetrahedron tetrahedron = new Tetrahedron(point1, point2, point3, point4);
        TetrahedronAction action = new TetrahedronAction(tetrahedron);
        double expectedRatio = 0.57142857142;
        Map<String, Double> actualIntersectionFactor = action.findIntersectionFactors().get();
        double actualRatio = actualIntersectionFactor.get("XZ");
        assertEquals(actualRatio, expectedRatio, 0.000_001);
    }

}