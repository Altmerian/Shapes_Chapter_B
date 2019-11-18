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
    public String toString() {
        return "Tetrahedron {" +
                "vertexes = " + Arrays.toString(vertexes) +
                '}';
    }

    @Override
    public void becomeChosen() {
        new TetrahedronAction(this).doAction();
    }

    public Point[] getVertexes() {
        return vertexes;
    }
}
