package by.epam.pavelshakhlovich.shape.inputdata;

import by.epam.pavelshakhlovich.shape.entity.FourPoints;
import by.epam.pavelshakhlovich.shape.entity.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DataParserTest {
    private final static String VALID_STRING = "1.0 -3.0 2.0; 5.0 -3.0 0.0; -1 -3.0 -3; 2.0 4.0 1.0";
    private final static String INVALID_STRING = "1.0a 3.0 4.5; 2.0  1.5  42; -1  -3 -54; 2.5 4.0 45";
    private static DataParser parser;
    private static Point[] pointsExpected;

    static {
        Point point1 = new Point(1.0, -3.0, 2.0);
        Point point2 = new Point(5.0, -3.0, 0.0);
        Point point3 = new Point(-1.0, -3.0, -3.0);
        Point point4 = new Point(2.0, 4.0, 1.0);
        pointsExpected = new Point[] {point1, point2, point3, point4};
    }

    @BeforeClass
    public static void setUp() {
        parser = new DataParser();
    }

    @Test
    public void testIsValid() {
        Assert.assertTrue(VALID_STRING.matches("(-?\\d[,.]*\\d*[;\\s]*){12}"), "Regex checking");
    }

    @Test
    public void testIsInValid() {
        Assert.assertFalse(INVALID_STRING.matches("(-?\\d[,.]*\\d*[;\\s]*){12}"));
    }

    @Test
    public void testParsePoints() {
        Point[] expected = pointsExpected;
        Point[] actual = parser.parsePoints(VALID_STRING);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testParseData() {
        List<String> lines = new ArrayList<>();
        lines.add(VALID_STRING);
        List<FourPoints> expected = new ArrayList<>();
        expected.add(new FourPoints(pointsExpected));
        List<FourPoints> actual = parser.parseData(lines);
        Assert.assertEquals(actual, expected);
    }
}