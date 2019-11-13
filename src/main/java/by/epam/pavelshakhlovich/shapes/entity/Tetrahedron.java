package by.epam.pavelshakhlovich.shapes.entity;

public class Tetrahedron extends Shape {
    private Point vertexA;
    private Point vertexB;
    private Point vertexC;
    private Point vertexD;

    public Tetrahedron(Point vertexA, Point vertexB, Point pointC, Point pointD) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.vertexC = pointC;
        this.vertexD = pointD;
    }


}
