package by.epam.pavelshakhlovich.shape.entity;

import java.util.Arrays;

public class UnknownShape extends Shape {

    public UnknownShape(Point[] points) {
        super(points);
    }

    @Override
    public String toString() {
        return "UnknownShape {" +
                "points = " + Arrays.toString(points) +
                '}';
    }

    @Override
    public void becomeChosen() {
    }

    public Point[] getPoints() {
        return points;
    }

}

