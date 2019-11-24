package by.epam.pavelshakhlovich.shape.entity;

import java.util.Arrays;

public class Tetrahedron extends Shape {

    public Tetrahedron(Point[] points) {
      super(points);
    }

    public Tetrahedron(Point pointA, Point pointB, Point pointC, Point pointD) {
        super(new Point[]{pointA, pointB, pointC, pointD});
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tetrahedron)) return false;
        Tetrahedron that = (Tetrahedron) o;
        return Arrays.deepEquals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }

    @Override
    public String toString() {
        return "Tetrahedron {" +
                "vertexes = " + Arrays.toString(points) +
                '}';
    }

}
