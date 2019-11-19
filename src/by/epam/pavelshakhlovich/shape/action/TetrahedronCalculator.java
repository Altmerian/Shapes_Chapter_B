package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.util.MathHelper;
import com.google.common.annotations.VisibleForTesting;

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
        this.sideAB = MathHelper.length(tetrahedron.getVertexes()[0], tetrahedron.getVertexes()[1]);
        this.sideBC = MathHelper.length(tetrahedron.getVertexes()[1], tetrahedron.getVertexes()[2]);
        this.sideCA = MathHelper.length(tetrahedron.getVertexes()[2], tetrahedron.getVertexes()[0]);
        this.sideAD = MathHelper.length(tetrahedron.getVertexes()[0], tetrahedron.getVertexes()[3]);
        this.sideBD = MathHelper.length(tetrahedron.getVertexes()[1], tetrahedron.getVertexes()[3]);
        this.sideCD = MathHelper.length(tetrahedron.getVertexes()[2], tetrahedron.getVertexes()[3]);
    }

    double calculateSurfaceArea() {
        double ABCTriangleArea = calculateTriangleArea(sideAB, sideBC, sideCA);
        double ABDTriangleArea = calculateTriangleArea(sideAB, sideAD, sideBD);
        double ACDTriangleArea = calculateTriangleArea(sideCA, sideAD, sideCD);
        double BCDTriangleArea = calculateTriangleArea(sideBC, sideBD, sideCD);
        return ABCTriangleArea + ABDTriangleArea + ACDTriangleArea + BCDTriangleArea;
    }

    @VisibleForTesting
    double calculateTriangleArea(double side1, double side2, double side3) {
        double p = (side1 + side2 + side3) / 2.0;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    @VisibleForTesting
    double calculateVolume() {
        return (1.0 / 6.0) * MathHelper.determinant(tetrahedron.getVertexes());
    }

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
