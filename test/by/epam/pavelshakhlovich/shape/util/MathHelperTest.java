package by.epam.pavelshakhlovich.shape.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathHelperTest {
    private double[] numbers =  {-3.0, -3.0, -3.0, 4.0};

    @Test
    public void testFindUniqueNumberIndex() {
        int expected = 3;
        assertEquals(MathHelper.findUniqueNumberIndex(numbers), expected);
    }

    @Test
    public void testHasThreeEqualNumbers() {
        assertTrue(MathHelper.hasThreeEqualNumbers(numbers));

    }
}