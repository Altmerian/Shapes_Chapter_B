package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;

import static java.lang.Math.*;

public class TetrahedronCalc {
    private double sideAB;
    private double sideBC;
    private double sideCA;
    private double sideAD;
    private double sideBD;
    private double sideCD;

    public TetrahedronCalc(Tetrahedron tetrahedron) {
        this.sideAB = sqrt(pow(tetrahedron.getVertexA().getX() - tetrahedron.getVertexB().getX(), 2) +
                pow(tetrahedron.getVertexA().getY() - tetrahedron.getVertexB().getY(), 2) +
                pow(tetrahedron.getVertexA().getZ() - tetrahedron.getVertexB().getZ(), 2));
        this.sideBC = sqrt(pow(tetrahedron.getVertexB().getX() - tetrahedron.getVertexC().getX(), 2) +
                pow(tetrahedron.getVertexB().getY() - tetrahedron.getVertexC().getY(), 2) +
                pow(tetrahedron.getVertexB().getZ() - tetrahedron.getVertexC().getZ(), 2));
        this.sideCA = sqrt(pow(tetrahedron.getVertexC().getX() - tetrahedron.getVertexA().getX(), 2) +
                pow(tetrahedron.getVertexC().getY() - tetrahedron.getVertexA().getY(), 2) +
                pow(tetrahedron.getVertexC().getZ() - tetrahedron.getVertexA().getZ(), 2));
        this.sideAD = sqrt(pow(tetrahedron.getVertexA().getX() - tetrahedron.getVertexD().getX(), 2) +
                pow(tetrahedron.getVertexA().getY() - tetrahedron.getVertexD().getY(), 2) +
                pow(tetrahedron.getVertexA().getZ() - tetrahedron.getVertexD().getZ(), 2));
        this.sideBD = sqrt(pow(tetrahedron.getVertexB().getX() - tetrahedron.getVertexD().getX(), 2) +
                pow(tetrahedron.getVertexB().getY() - tetrahedron.getVertexD().getY(), 2) +
                pow(tetrahedron.getVertexB().getZ() - tetrahedron.getVertexD().getZ(), 2));
        this.sideCD = sqrt(pow(tetrahedron.getVertexC().getX() - tetrahedron.getVertexD().getX(), 2) +
                pow(tetrahedron.getVertexC().getY() - tetrahedron.getVertexD().getY(), 2) +
                pow(tetrahedron.getVertexC().getZ() - tetrahedron.getVertexD().getZ(), 2));
    }

    public double calculateSurfaceArea() {
        double ABCTriangleArea = calculateTriangleArea(sideAB, sideBC, sideCA);

        return ABCTriangleArea;
    }

    private double calculateTriangleArea(double sideAB, double sideBC, double sideCA) {
        double p = (sideAB + sideBC + sideCA) / 2.0;
        return Math.sqrt(p * (p - sideAB) * (p - sideBC) * (p - sideCA));
    }
}
