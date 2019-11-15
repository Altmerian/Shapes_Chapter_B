package by.epam.pavelshakhlovich.shape.entity;

public class Tetrahedron extends Shape {
    private Point vertexA;
    private Point vertexB;
    private Point vertexC;
    private Point vertexD;


    public Tetrahedron(Point pointA, Point pointB, Point pointC, Point pointD) {
        this.vertexA = pointA;
        this.vertexB = pointB;
        this.vertexC = pointC;
        this.vertexD = pointD;
    }

    public Point getVertexA() {
        return vertexA;
    }

    public Point getVertexB() {
        return vertexB;
    }

    public Point getVertexC() {
        return vertexC;
    }

    public Point getVertexD() {
        return vertexD;
    }
}
