package by.epam.pavelshakhlovich.shape.factory;

public enum ShapeType {
    TETRAHEDRON("[Tt][Ee][Tt][Rr][Aa][Hh][Ee][Dd][Rr][Oo][Nn]"),
    UNKNOWN ("Unknown");

    private String regex;

    ShapeType(String regex) {
    }

    public String getRegex() {
        return regex;
    }
}
