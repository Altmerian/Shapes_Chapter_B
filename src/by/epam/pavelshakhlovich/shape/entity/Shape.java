package by.epam.pavelshakhlovich.shape.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape)) return false;

        Shape shape = (Shape) o;

        return Arrays.deepEquals(points, shape.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }

    @Override
    public int compareTo(@NotNull Shape shape) {
        return Integer.compare(id, shape.getId());
    }
}
