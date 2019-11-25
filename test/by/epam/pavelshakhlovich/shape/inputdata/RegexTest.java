package by.epam.pavelshakhlovich.shape.inputdata;

import org.testng.Assert;

import org.testng.annotations.Test;

public class RegexTest {
    private final static String VALID_LINE =
            "Tetrahedron -2.0 -0.0 1.0; 5.0 6.0 1.0; -10.0 9.0 7.0; 3.0 -5.0 1.0";
    private final static String INVALID_LINE =
            "\"Tetrahedron 1.0a 3.0 4.5; 2.0  1.5  42; -1  -3 -54; 2.5 4.0 45 invalid line\"";

    @Test
    public void lineMatchesTest() {
        String regex = "([Tt][Ee][Tt][Rr][Aa][Hh][Ee][Dd][Rr][Oo][Nn]\\s+)((-?\\d+[,.]*\\d*[;\\s]*){12})";
        Assert.assertTrue(VALID_LINE.matches(regex));
    }

    @Test
    public void lineNotMatchesTest() {
        String regex = "([Tt][Ee][Tt][Rr][Aa][Hh][Ee][Dd][Rr][Oo][Nn]\\s+)((-?\\d+[,.]*\\d*[;\\s]*){12})";
        Assert.assertFalse(INVALID_LINE.matches(regex));
    }


}
