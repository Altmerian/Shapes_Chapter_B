package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.util.MathHelper;
import com.google.common.annotations.VisibleForTesting;

public class TetrahedronCalculator {
    private Tetrahedron tetrahedron;


    public TetrahedronCalculator(Tetrahedron tetrahedron) {
        this.tetrahedron = tetrahedron;
    }

    public double calculateSurfaceArea() {
        double TriangleArea1 = calculateTriangleArea(
                tetrahedron.getPoints()[0], tetrahedron.getPoints()[1], tetrahedron.getPoints()[2]);
        double TriangleArea2 = calculateTriangleArea(
                tetrahedron.getPoints()[0], tetrahedron.getPoints()[2], tetrahedron.getPoints()[3]);
        double TriangleArea3 = calculateTriangleArea(
                tetrahedron.getPoints()[1], tetrahedron.getPoints()[2], tetrahedron.getPoints()[3]);
        double TriangleArea4 = calculateTriangleArea(
                tetrahedron.getPoints()[0], tetrahedron.getPoints()[1], tetrahedron.getPoints()[3]);
        return TriangleArea1 + TriangleArea2 + TriangleArea3 + TriangleArea4;
    }

    @VisibleForTesting
    double calculateTriangleArea(Point vertexes1, Point vertexes2, Point vertexes3) {
        double side1 = MathHelper.length(vertexes1, vertexes2);
        double side2 = MathHelper.length(vertexes2, vertexes3);
        double side3 = MathHelper.length(vertexes3, vertexes1);
        double p = (side1 + side2 + side3) / 2.0;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    public double calculateVolume() {
        return (1.0 / 6.0) * MathHelper.determinant(tetrahedron.getPoints());
    }

}
