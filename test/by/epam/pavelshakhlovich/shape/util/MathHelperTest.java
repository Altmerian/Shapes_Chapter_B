package by.epam.pavelshakhlovich.shape.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathHelperTest {

    @Test
    public void testFindUniqueNumberIndex() {
        double [] numbers = new double[] {-3.0, -3.0, -3.0, 4.0};
        int expected = 3;
        assertEquals(MathHelper.findUniqueNumberIndex(numbers), expected);
    }
}