package by.epam.pavelshakhlovich.shapes.entity;

public class Point {
    private double x;
    private double y;
    private double z;

    Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public double getY() {
        return y;
    }
}
