package by.epam.pavelshakhlovich.shape.entity;

import java.util.Arrays;

public class UnknownShape extends Shape {
    private Point[] vertexes = new Point[2];


    public UnknownShape(Point pointA, Point pointB) {
        this.vertexes[0] = pointA;
        this.vertexes[1] = pointB;
    }

    @Override
    public String toString() {
        return "UnknownShape {" +
                "points = " + Arrays.toString(vertexes) +
                '}';
    }

    @Override
    public void becomeChosen() {
        System.out.printf("Sorry, logic for %s is not ready yet.", this.getClass().getSimpleName());
    }

    public Point[] getVertexes() {
        return vertexes;
    }
}
