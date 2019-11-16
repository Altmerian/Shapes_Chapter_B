package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.util.MathHelper;

import static java.lang.Math.*;

public class TetrahedronCalculator {
    private double sideAB;
    private double sideBC;
    private double sideCA;
    private double sideAD;
    private double sideBD;
    private double sideCD;

    public TetrahedronCalculator(Tetrahedron tetrahedron) {
        this.sideAB = MathHelper.getDistance(tetrahedron.getVertexA(), tetrahedron.getVertexB());
        this.sideBC = MathHelper.getDistance(tetrahedron.getVertexB(), tetrahedron.getVertexC());
        this.sideCA = MathHelper.getDistance(tetrahedron.getVertexC(), tetrahedron.getVertexA());
        this.sideAD = MathHelper.getDistance(tetrahedron.getVertexA(), tetrahedron.getVertexD());
        this.sideBD = MathHelper.getDistance(tetrahedron.getVertexB(), tetrahedron.getVertexD());
        this.sideCD = MathHelper.getDistance(tetrahedron.getVertexC(), tetrahedron.getVertexD());
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
