package by.epam.pavelshakhlovich.shape.entity;

import by.epam.pavelshakhlovich.shape.action.TetrahedronAction;

import java.util.Arrays;

public class Tetrahedron extends Shape {

    private Point[] vertexes = new Point[4];

    public Tetrahedron(Point[] vertexes) {
        this.vertexes = vertexes;
    }

    public Tetrahedron(Point pointA, Point pointB, Point pointC, Point pointD) {
        this.vertexes[0] = pointA;
        this.vertexes[1] = pointB;
        this.vertexes[2] = pointC;
        this.vertexes[3] = pointD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tetrahedron)) return false;
        Tetrahedron that = (Tetrahedron) o;
        return Arrays.deepEquals(vertexes, that.vertexes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vertexes);
    }

    @Override
    public String toString() {
        return "Tetrahedron {" +
                "vertexes = " + Arrays.toString(vertexes) +
                '}';
    }

    @Override
    public void becomeChosen() {
        new TetrahedronAction(this).doAction();
    }

    public void setVertexes(Point[] vertexes) {
        this.vertexes = vertexes;
    }

    public Point[] getVertexes() {
        return vertexes;
    }

}
