package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.util.MathHelper;
import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class TetrahedronAction {
    private Tetrahedron tetrahedron;
    private TetrahedronCalculator calculator;
    private double volume;
    private static Logger logger = LogManager.getLogger();

    public TetrahedronAction(Tetrahedron tetrahedron) {
        this.tetrahedron = tetrahedron;
        this.calculator = new TetrahedronCalculator(tetrahedron);
    }

    public void doAction() {

        if (isDegenerate()) {
            logger.warn("One or more vertexes have the same coordinates or all vertexes lie at the same plane. " +
                    "\nTetrahedron is degenerate and doesn`t have any surface area or volume!");
            return;
        } else {
            volume = calculator.calculateVolume();
            logger.printf(Level.INFO, "Surface area: %f m2, volume: %f m3",
                    calculator.calculateSurfaceArea(), volume);
        }

        if (findIntersectionFactors().isPresent()) {
            Map<String, Double> factors = findIntersectionFactors().get();
            calculateVolumeParts(factors);
        } else if (!lieOnCoordinatePlane().isEmpty()) {
            logger.info("Basis of the Tetrahedron lies on the coordinate planes: " +
                    lieOnCoordinatePlane().toString());
        } else {
            logger.info("The Tetrahedron does not intersect with any coordinate plane.");
        }
    }


    private void calculateVolumeParts(Map<String, Double> factors) {
        for (Map.Entry<String, Double> factor : factors.entrySet()) {
            double upperVolume = volume * Math.pow(factor.getValue(), 3);
            logger.printf(Level.INFO,
                    "Coordinate plane %s divides the Tetrahedron onto two shapes with volumes %f m3/ %f m3",
                    factor.getKey(), upperVolume, volume - upperVolume);
        }
    }

    public boolean isDegenerate() {
        Point[] vertexes = tetrahedron.getPoints();
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
        HashSet<Point> equalTestSet = new HashSet<>(Arrays.asList(vertexes));
        boolean pointsEqualCause = equalTestSet.size() != vertexes.length;
        return xCause || yCause || zCause || pointsEqualCause;
    }

    @VisibleForTesting
    Optional<Map<String, Double>> findIntersectionFactors() {
        Point[] vertexes = tetrahedron.getPoints();
        Map<String, Double> intersectionFactors = new HashMap<>();

        int xCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getX() < 0) {
                xCause++;
            }
        }
        boolean basisIsParallelToThePlane = false;
        double[] coordinatesX = {vertexes[0].getX(), vertexes[1].getX(), vertexes[2].getX(), vertexes[3].getX()};
        if (MathHelper.hasThreeEqualNumbers(coordinatesX)) {
            basisIsParallelToThePlane = true;
        }
        if (basisIsParallelToThePlane && (xCause == 3 || xCause == 1)) {
            int mainVertexIndex = MathHelper.findUniqueNumberIndex(coordinatesX);
            List<Point> basisPoints = Arrays.stream(vertexes)
                    .filter(x -> x != vertexes[mainVertexIndex])
                    .collect(Collectors.toList());
            double height = vertexes[mainVertexIndex].getX() - basisPoints.get(0).getX();
            double ratio = vertexes[mainVertexIndex].getX() / height;
            intersectionFactors.put("YZ", ratio);
        }

        int yCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getY() < 0) {
                yCause++;
            }
        }
        basisIsParallelToThePlane = false;
        double[] coordinatesY = {vertexes[0].getY(), vertexes[1].getY(), vertexes[2].getY(), vertexes[3].getY()};
        if (MathHelper.hasThreeEqualNumbers(coordinatesY)) {
            basisIsParallelToThePlane = true;
        }
        if (basisIsParallelToThePlane && (yCause == 3 || yCause == 1)) {
            int mainVertexIndex = MathHelper.findUniqueNumberIndex(coordinatesY);
            List<Point> basisPoints = Arrays.stream(vertexes)
                    .filter(y -> y != vertexes[mainVertexIndex])
                    .collect(Collectors.toList());
            double height = vertexes[mainVertexIndex].getY() - basisPoints.get(0).getY();
            double ratio = vertexes[mainVertexIndex].getY() / height;
            intersectionFactors.put("XZ", ratio);
        }

        int zCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getZ() < 0) {
                zCause++;
            }
        }
        basisIsParallelToThePlane = false;
        double[] coordinatesZ = {vertexes[0].getZ(), vertexes[1].getZ(), vertexes[2].getZ(), vertexes[3].getZ()};
        if (MathHelper.hasThreeEqualNumbers(coordinatesZ)) {
            basisIsParallelToThePlane = true;
        }
        if (basisIsParallelToThePlane && (zCause == 3 || zCause == 1)) {
            int mainVertexIndex = MathHelper.findUniqueNumberIndex(coordinatesZ);
            List<Point> basisPoints = Arrays.stream(vertexes)
                    .filter(z -> z != vertexes[mainVertexIndex])
                    .collect(Collectors.toList());
            double height = vertexes[mainVertexIndex].getZ() - basisPoints.get(0).getZ();
            double ratio = vertexes[mainVertexIndex].getZ() / height;
            intersectionFactors.put("XY", ratio);
        }

        if (intersectionFactors.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(intersectionFactors);
        }

    }

    @VisibleForTesting
    List<String> lieOnCoordinatePlane() {
        List<String> coordinatePlaneNames = new ArrayList<>();
        Point[] vertexes = tetrahedron.getPoints();
        int xCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getX() == 0) {
                xCause++;
            }
        }
        if (xCause == 3) {
            coordinatePlaneNames.add("YZ");
        }

        int yCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getY() == 0) {
                yCause++;
            }
        }
        if (yCause == 3) {
            coordinatePlaneNames.add("XZ");
        }

        int zCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getZ() == 0) {
                zCause++;
            }
        }
        if (zCause == 3) {
            coordinatePlaneNames.add("XY");
        }
        return coordinatePlaneNames;
    }

}


