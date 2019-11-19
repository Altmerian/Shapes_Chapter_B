package by.epam.pavelshakhlovich.shape.inputdata;

import by.epam.pavelshakhlovich.shape.entity.EmptyDataException;
import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.factory.ShapeType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataParserTest {
    private final static String VALID_STRING = "Tetrahedron 2.0 0.0 1.0; 5.0 6.0 1.0; 10.0 9.0 7.0; 3.0 5.0 1.0";
    private final static String INVALID_STRING = "Tetrahedron 1.0a 3.0 4.5; 2.0  1.5  42; -1  -3 -54; 2.5 4.0 45";
    private static DataParser parser;
    private static Point[] pointsExpected;

    static {
        Point point1 = new Point(2.0, 0.0, 1.0);
        Point point2 = new Point(5.0, 6.0, 1.0);
        Point point3 = new Point(10.0, 9.0, 7.0);
        Point point4 = new Point(3.0, 5.0, 1.0);
        pointsExpected = new Point[] {point1, point2, point3, point4};
    }

    @BeforeClass
    public static void setUp() {
        parser = new DataParser();
    }

    @Test
    public void testParseShapeType() {
        ShapeType expectedShapeType = ShapeType.TETRAHEDRON;
        Optional<ShapeType> type = parser.parseShapeType(VALID_STRING);
        ShapeType actualShapeType = null;
        if (type.isPresent()) {
            actualShapeType = type.get();
        }
        Assert.assertEquals(actualShapeType, expectedShapeType, "Regex checking");
    }

    @Test
    public void IsInValidLineTest() {
        Assert.assertFalse(parser.parseShapeType(INVALID_STRING).isPresent());
    }

    @Test
    public void testParsePoints() {
        Point[] expected = pointsExpected;
        Point[] actual = parser.parsePoints(VALID_STRING, ShapeType.TETRAHEDRON);
        Assert.assertEquals(actual, expected);
    }

    @Test (expectedExceptions = EmptyDataException.class)
    public void testEmptyDataException (){
        List<String> lines = new ArrayList<>();
        lines.add(INVALID_STRING);
        parser.parseData(lines);
    }


    @Test
    public void testParseData() {
        List<String> lines = new ArrayList<>();
        lines.add(VALID_STRING);
        List<ShapeType> expectedShapes = new ArrayList<>();
        expectedShapes.add(ShapeType.TETRAHEDRON);
        List<Point[]> expectedPointsGroup = new ArrayList<>();
        expectedPointsGroup.add(pointsExpected);
        DataObject expected = new DataObject(expectedShapes, expectedPointsGroup);
        DataObject actual = parser.parseData(lines);
        Assert.assertEquals(actual, expected);
    }
}