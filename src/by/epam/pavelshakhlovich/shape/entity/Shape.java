package by.epam.pavelshakhlovich.shape.entity;

public abstract class Shape {
    protected Point[] points;

    public Shape(Point[] points) {
        this.points = points;
    }

    public abstract void becomeChosen();

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

}
