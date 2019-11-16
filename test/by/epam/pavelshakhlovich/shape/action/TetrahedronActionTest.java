package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TetrahedronActionTest {
    private static Tetrahedron tetrahedron;

    static {
        Point point1 = new Point(1.0, -3.0, 2.0);
        Point point2 = new Point(5.0, -3.0, 0.0);
        Point point3 = new Point(-1.0, -3.0, -3.0);
        Point point4 = new Point(1.0, -3.0, 2.0);
        tetrahedron = new Tetrahedron(point1, point2, point3, point4);
    }

    @BeforeMethod
    public static void setUp() {

    }

    @Test
    public void testDoAction() {

    }

    @Test
    public void testIsDegenerate() {

    }
}