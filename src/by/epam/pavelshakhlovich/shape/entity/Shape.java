package by.epam.pavelshakhlovich.shape.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape {
    private Point[] points;


    public abstract void becomeChosen();

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public String getRuntimeType() {
        return this.getClass().getSimpleName();
    }



}
