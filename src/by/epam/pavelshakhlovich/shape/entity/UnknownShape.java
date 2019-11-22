package by.epam.pavelshakhlovich.shape.entity;

import java.util.Arrays;

public class UnknownShape extends Shape {
    private Point[] points = new Point[2];


    public UnknownShape(Point[] points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "UnknownShape {" +
                "points = " + Arrays.toString(points) +
                '}';
    }

    @Override
    public void becomeChosen() {
        System.out.printf("Sorry, logic for %s is not ready yet.\n", this.getClass().getSimpleName());
    }

    public Point[] getPoints() {
        return points;
    }

}

