package by.epam.pavelshakhlovich.shape.entity;

import by.epam.pavelshakhlovich.shape.action.TetrahedronAction;

import java.util.Arrays;

public class Tetrahedron extends Shape {

    private Point[] points = new Point[4];

    public Tetrahedron(Point[] points) {
        this.points = points;
    }

    public Tetrahedron(Point pointA, Point pointB, Point pointC, Point pointD) {
        this.points[0] = pointA;
        this.points[1] = pointB;
        this.points[2] = pointC;
        this.points[3] = pointD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tetrahedron)) return false;
        Tetrahedron that = (Tetrahedron) o;
        return Arrays.deepEquals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }

    @Override
    public String toString() {
        return "Tetrahedron {" +
                "vertexes = " + Arrays.toString(points) +
                '}';
    }

    @Override
    public void becomeChosen() {
        new TetrahedronAction(this).doAction();
    }

}
