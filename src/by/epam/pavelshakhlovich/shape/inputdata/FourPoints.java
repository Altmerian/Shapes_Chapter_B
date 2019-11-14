package by.epam.pavelshakhlovich.shape.inputdata;

import by.epam.pavelshakhlovich.shape.entity.Point;

public class FourPoints {
    private Point[] points;

    FourPoints(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }
}
