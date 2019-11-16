package by.epam.pavelshakhlovich.shape.inputdata;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DataReaderTest {

    @Test
    public void testReadLinesFromFile() {
        Path path = Paths.get("data/data.txt");
        List<String> expected = Arrays.asList(
                "Tetrahedron 1.0 -3.0 2.0; 5.0 -3.0 0.0; -1 -3.0 -3; 2.0 4.0 1.0",
                "Tetrahedron 1.0a 3.0 4.5; 2.0  1.5  42; -1  -3 -54; 2.5 4.0 45 invalid line",
                "Tetrahedron -2.0 1.0 4.0; -2.0 5.0 4.0; -2.0 -1.0 4.0; -2.0 1.0 4.0",
                "Unknown 2.0 1.0 3.0  4.0 5.0 6.0");
        DataReader reader = new DataReader();
        List<String> actual = reader.readLinesFromFile(path);
        Assert.assertEquals(actual, expected);
    }
}