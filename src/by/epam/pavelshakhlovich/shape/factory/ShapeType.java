package by.epam.pavelshakhlovich.shape.factory;

public enum ShapeType {
    TETRAHEDRON(4, "([Tt][Ee][Tt][Rr][Aa][Hh][Ee][Dd][Rr][Oo][Nn]\\s+)((-?\\d+[,.]*\\d*[;\\s]*){12})"),
    UNKNOWN (2, "([Uu][Nn][Kk][Nn][Oo][Ww][Nn]\\s+)((-?\\d+[,.]*\\d*[;\\s]*){6})");

    private int pointsQuantity;
    private String regex;

    ShapeType(int pointsQuantity, String regex) {
        this.pointsQuantity = pointsQuantity;
        this.regex = regex;
    }

    public int getPointsQuantity() {
        return pointsQuantity;
    }

    public String getRegex() {
        return regex;
    }
}
