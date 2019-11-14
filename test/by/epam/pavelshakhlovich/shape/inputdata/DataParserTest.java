package by.epam.pavelshakhlovich.shape.inputdata;

import by.epam.pavelshakhlovich.shape.entity.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DataParserTest {
    private final static String STRING = "1.0 -3.0 2.0; 5.0 -3.0 0.0; -1 -3.0 -3; 2.0 4.0 1.0";

//    @BeforeClass
//    public void setUp() {
//        DataParser parser = new DataParser();
//    }

    @Test
    public void testParseData() {
        List<String> lines = new ArrayList<>();
        lines.add(STRING);
        DataParser parser = new DataParser();
        List<FourPoints> expected = new ArrayList<>();
        Point point1 = new Point(1.0, -3.0, 2.0);
        Point point2 = new Point(1.0, -3.0, 2.0);
        Point point3 = new Point(1.0, -3.0, 2.0);
        Point point4 = new Point(1.0, -3.0, 2.0);
        FourPoints points = new FourPoints(new Point[]{point1, point2, point3, point4});
        expected.add(points);
        List<FourPoints> actual = new ArrayList<>();
        actual = parser.parseData(lines);
        Assert.assertEquals(actual,expected);
    }
}