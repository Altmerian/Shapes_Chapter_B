package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.util.MathHelper;
import com.google.common.annotations.VisibleForTesting;

import java.util.*;
import java.util.stream.Collectors;

public class TetrahedronAction {
    private Tetrahedron tetrahedron;
    private TetrahedronCalculator calculator;
    private String coordinatePlaneName;
    private double volume;


    public TetrahedronAction(Tetrahedron tetrahedron) {
        this.tetrahedron = tetrahedron;
        this.calculator = new TetrahedronCalculator(tetrahedron);
    }

    public void doAction() {

        if (isDegenerate()) {
            System.out.print("One or more vertexes have the same coordinates or all vertexes lie at the same plane.");
            System.out.println(" Tetrahedron is degenerate and doesn`t have any surface area or volume!");
            return;
        } else {
            System.out.printf("Surface area: %f m2", calculator.calculateSurfaceArea());
            volume = calculator.calculateVolume();
            System.out.printf(", volume: %f m3\n", volume);
        }

        if (findIntersectionFactors().isPresent()) {
            Map<String, Double> factors = findIntersectionFactors().get();
            findVolumeParts(factors);
        } else if (lieOnCoordinatePlane()) {
            System.out.println("Basis of the Tetrahedron lies on the coordinate plane " + coordinatePlaneName);
        } else {
            System.out.println("The Tetrahedron does not intersect with or lie at any coordinate plane.");
        }
    }

    private void findVolumeParts(Map<String, Double> factors) {
        for (Map.Entry<String, Double> factor : factors.entrySet()) {
            double upperVolume = volume * Math.pow(factor.getValue(), 3);
            System.out.printf("Coordinate plane %s divides the Tetrahedron onto two shapes with volumes %f m3/ %f m3\n",
                    factor.getKey(), upperVolume, volume - upperVolume);
        }
    }


    @VisibleForTesting
    boolean isDegenerate() {

        Point[] vertexes = tetrahedron.getVertexes();
        double x = vertexes[0].getX();
        boolean xCause = true;
        for (int i = 1; i < 4; i++) {
            if (vertexes[i].getX() != x) {
                xCause = false;
                break;
            }
        }

        double y = vertexes[0].getY();
        boolean yCause = true;
        for (int i = 1; i < 4; i++) {
            if (vertexes[i].getY() != y) {
                yCause = false;
                break;
            }
        }

        double z = vertexes[0].getZ();
        boolean zCause = true;
        for (int i = 1; i < 4; i++) {
            if (vertexes[i].getZ() != z) {
                zCause = false;
                break;
            }
        }

        HashSet<Point> equalTestSet = new HashSet<>();
        equalTestSet.add(tetrahedron.getVertexes()[0]);
        boolean pointsEqualCause = false;
        for (int i = 1; i < 4; i++) {
            if (!equalTestSet.add(tetrahedron.getVertexes()[i])) {
                pointsEqualCause = true;
                break;
            }
        }

        return xCause || yCause || zCause || pointsEqualCause;
    }


    private Optional<Map<String, Double>> findIntersectionFactors() {
        Point[] vertexes = tetrahedron.getVertexes();
        Map<String, Double> intersectionFactors = new HashMap<>();


        int xCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getX() < 0) {
                xCause++;
            }
        }
        if (xCause == 3 || xCause == 1) {
            coordinatePlaneName = "YZ";
            int mainVertexIndex = MathHelper.findUniqueNumberIndex(new double[]
                    {vertexes[0].getX(), vertexes[1].getX(), vertexes[2].getX(), vertexes[3].getX()});
            List<Point> basisPoints = Arrays.stream(vertexes)
                    .filter(x -> x != vertexes[mainVertexIndex])
                    .collect(Collectors.toList());
            double height = volume * 3 / calculator.calculateTriangleArea(basisPoints);
            double ratio = vertexes[mainVertexIndex].getX() / height;
            intersectionFactors.put(coordinatePlaneName, ratio);
        }


        int yCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getY() < 0) {
                yCause++;
            }
        }
        if (yCause == 3 || yCause == 1) {
            coordinatePlaneName = "XZ";
            int mainVertexIndex = MathHelper.findUniqueNumberIndex(new double[]
                    {vertexes[0].getY(), vertexes[1].getY(), vertexes[2].getY(), vertexes[3].getY()});
            List<Point> basisPoints = Arrays.stream(vertexes)
                    .filter(y -> y != vertexes[mainVertexIndex])
                    .collect(Collectors.toList());
            double height = volume * 3 / calculator.calculateTriangleArea(basisPoints);
            double ratio = vertexes[mainVertexIndex].getY() / height;
            intersectionFactors.put(coordinatePlaneName, ratio);
        }

        int zCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getZ() < 0) {
                zCause++;
            }
        }
        if (zCause == 3 || zCause == 1) {
            coordinatePlaneName = "XY";
            int mainVertexIndex = MathHelper.findUniqueNumberIndex(new double[]
                    {vertexes[0].getZ(), vertexes[1].getZ(), vertexes[2].getZ(), vertexes[3].getZ()});
            List<Point> basisPoints = Arrays.stream(vertexes)
                    .filter(z -> z != vertexes[mainVertexIndex])
                    .collect(Collectors.toList());
            double height = volume * 3 / calculator.calculateTriangleArea(basisPoints);
            double ratio = vertexes[mainVertexIndex].getZ() / height;
            intersectionFactors.put(coordinatePlaneName, ratio);
        }

        if (intersectionFactors.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(intersectionFactors);
        }

    }


    private boolean lieOnCoordinatePlane() {
        Point[] vertexes = tetrahedron.getVertexes();
        int xCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getX() == 0) {
                xCause++;
            }
        }
        if (xCause == 3) {
            coordinatePlaneName = "YZ";
            return true;
        }

        int yCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getY() == 0) {
                yCause++;
            }
        }
        if (yCause == 3) {
            coordinatePlaneName = "XZ";
            return true;
        }

        int zCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getZ() == 0) {
                zCause++;
            }
        }
        if (zCause == 3) {
            coordinatePlaneName = "XY";
            return true;
        }

        return false;
    }

}


