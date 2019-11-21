package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TetrahedronCalculatorTest {
    private static Tetrahedron tetrahedron;
    private static TetrahedronCalculator calculator;

    static {
        Point point1 = new Point(1.0, -3.0, 2.0);
        Point point2 = new Point(5.0, -3.0, 0.0);
        Point point3 = new Point(-1.0, -3.0, -3.0);
        Point point4 = new Point(2.0, 4.0, 1.0);
        tetrahedron = new Tetrahedron(point1, point2, point3, point4);
    }
    @BeforeMethod
    public void setUp() {
        calculator = new TetrahedronCalculator(tetrahedron);
    }

    @Test
    public void testCalculateSurfaceArea() {
        double excepted = 12 + 15.684387141358119 + 19.17028951268081 + 24.64751508773248;
        double actual = calculator.calculateSurfaceArea();
        assertEquals(actual, excepted, 0.000_001);
    }

    @Test
    public void testCalculateTriangleArea() {
        double excepted = 24.64751508773248;
        double actual = calculator.calculateTriangleArea(
                tetrahedron.getPoints()[1], tetrahedron.getPoints()[2], tetrahedron.getPoints()[3]);
        assertEquals(actual, excepted, 0.000_001);
    }

    @Test
    public void testCalculateVolume() {
        double excepted = 28.0;
        double actual = calculator.calculateVolume();
        assertEquals(actual, excepted, 0.000_001);
    }
}