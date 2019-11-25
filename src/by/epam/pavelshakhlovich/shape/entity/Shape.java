package by.epam.pavelshakhlovich.shape.entity;

import org.jetbrains.annotations.NotNull;

public abstract class Shape implements Comparable<Shape>{
    private int id; //technical ID, doesn't affect equals and hashcode
    protected Point[] points;

    public Shape(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(@NotNull Shape shape) {
        return Integer.compare(id, shape.getId());
    }
}
