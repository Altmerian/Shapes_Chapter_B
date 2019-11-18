package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import com.google.common.annotations.VisibleForTesting;

import java.util.HashSet;
import java.util.Optional;

public class TetrahedronAction {
    private Tetrahedron tetrahedron;
    private TetrahedronCalculator calculator;
    private String coordinatePlaneName;

    public TetrahedronAction(Tetrahedron tetrahedron) {
        this.tetrahedron = tetrahedron;
    }

    public void doAction() {

        if (isDegenerate()) {
            System.out.print("One or more vertexes have the same coordinates or all vertexes lie at the same plane.");
            System.out.println(" Tetrahedron is degenerate and doesn`t have any surface area or volume!");
        } else {
            calculator = new TetrahedronCalculator(tetrahedron);
            System.out.printf("Surface area: %f m2", calculator.calculateSurfaceArea());
            System.out.printf(", volume: %f m3\n", calculator.calculateVolume());
        }

        if (findIntersectionPoints().isPresent()) {
            double[] volumes = calculator.calculateIntersection(findIntersectionPoints().get());
            System.out.printf("Coordinate plane %s divides the Tetrahedron onto two shapes with volumes %f m3/ %f m3\n",
                    coordinatePlaneName, volumes[0], volumes[1]);
        } else if (lieOnCoordinatePlane()) {
            System.out.println("Basis of the Tetrahedron lies on the coordinate plane " + coordinatePlaneName);
        } else {
            System.out.println("The Tetrahedron does not intersect with any coordinate plane.");
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

    //todo this
    private Optional<Point[]> findIntersectionPoints() {
        Point[] vertexes = tetrahedron.getVertexes();
        int xCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getX() == 0) {
                xCause++;
            }
        }
        if (xCause == 3) {
            coordinatePlaneName = "Z";
            return Optional.of(vertexes);
        }

        return Optional.empty();
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
            coordinatePlaneName = "Z";
            return true;
        }

        int yCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getY() == 0) {
                yCause++;
            }
        }
        if (yCause == 3) {
            coordinatePlaneName = "X";
            return true;
        }

        int zCause = 0;
        for (int i = 0; i < 4; i++) {
            if (vertexes[i].getZ() == 0) {
                zCause++;
            }
        }
        if (zCause == 3) {
            coordinatePlaneName = "Y";
            return true;
        }

        return false;
    }

}


