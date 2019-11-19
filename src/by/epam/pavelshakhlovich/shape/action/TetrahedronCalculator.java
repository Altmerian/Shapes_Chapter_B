package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.util.MathHelper;
import com.google.common.annotations.VisibleForTesting;

import java.util.Arrays;
import java.util.List;

public class TetrahedronCalculator {
    private Tetrahedron tetrahedron;
    private double sideAB;
    private double sideBC;
    private double sideCA;
    private double sideAD;
    private double sideBD;
    private double sideCD;

    TetrahedronCalculator(Tetrahedron tetrahedron) {
        this.tetrahedron = tetrahedron;
        this.sideAB = MathHelper.distance(tetrahedron.getVertexes()[0], tetrahedron.getVertexes()[1]);
        this.sideBC = MathHelper.distance(tetrahedron.getVertexes()[1], tetrahedron.getVertexes()[2]);
        this.sideCA = MathHelper.distance(tetrahedron.getVertexes()[2], tetrahedron.getVertexes()[0]);
        this.sideAD = MathHelper.distance(tetrahedron.getVertexes()[0], tetrahedron.getVertexes()[3]);
        this.sideBD = MathHelper.distance(tetrahedron.getVertexes()[1], tetrahedron.getVertexes()[3]);
        this.sideCD = MathHelper.distance(tetrahedron.getVertexes()[2], tetrahedron.getVertexes()[3]);
    }

    double calculateSurfaceArea() {
        double ABCTriangleArea = calculateTriangleArea(sideAB, sideBC, sideCA);
        double ABDTriangleArea = calculateTriangleArea(sideAB, sideAD, sideBD);
        double ACDTriangleArea = calculateTriangleArea(sideCA, sideAD, sideCD);
        double BCDTriangleArea = calculateTriangleArea(sideBC, sideBD, sideCD);
        return ABCTriangleArea + ABDTriangleArea + ACDTriangleArea + BCDTriangleArea;
    }

    @VisibleForTesting
    double calculateTriangleArea(double sideAB, double sideBC, double sideCA) {
        double p = (sideAB + sideBC + sideCA) / 2.0;
        return Math.sqrt(p * (p - sideAB) * (p - sideBC) * (p - sideCA));
    }

    @VisibleForTesting
    double calculateVolume() {
        return (1.0 / 6.0) * MathHelper.determinant(tetrahedron.getVertexes());
    }

//    double calculateTriangleArea(List <Point> points) {
//        Point[] vertexes = points.toArray(new Point[3]);
//        double side1 = MathHelper.distance(vertexes[0], vertexes[1]);
//        double side2 = MathHelper.distance(vertexes[1], vertexes[2]);
//        double side3 = MathHelper.distance(vertexes[2], vertexes[0]);
//        double p = (side1 + side2 + side3) / 2.0;
//        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
//    }


    public double getSideAB() {
        return sideAB;
    }

    public double getSideBC() {
        return sideBC;
    }

    public double getSideCA() {
        return sideCA;
    }

    public double getSideAD() {
        return sideAD;
    }

    public double getSideBD() {
        return sideBD;
    }

    public double getSideCD() {
        return sideCD;
    }
}
