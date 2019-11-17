package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import com.google.common.annotations.VisibleForTesting;

import java.util.HashSet;

public class TetrahedronAction {
    private Tetrahedron tetrahedron;
    private String coordinatePlaneName;

    public TetrahedronAction(Tetrahedron tetrahedron) {
        this.tetrahedron = tetrahedron;
    }

    public void doAction() {

        if (isDegenerate()) {
            System.out.print("One or more vertexes have the same coordinates or all vertexes lie at the same plane.");
            System.out.println(" Tetrahedron is degenerate and doesn`t have any surface area or volume!");
        } else {
            TetrahedronCalculator calculator = new TetrahedronCalculator(tetrahedron);
            double area = calculator.calculateSurfaceArea();
            System.out.print("Surface area: " + area);
            double volume = calculator.calculateVolume();
            System.out.println(", volume: " + volume);
        }

        if (isIntersected()) {
            double [] volumes = TetrahedronCalculator.calculateIntersection();
            System.out.printf("Coordinate plane %s divides the Tetrahedron onto two shapes with volumes %f / %f",
                    coordinatePlaneName, volumes[0], volumes[1]);
        } else if (lieOnCoordinatePlane()) {
            System.out.println("Basic of the Tetrahedron lies on the coordinate plane " + coordinatePlaneName);
        } else {
            System.out.println("The Tetrahedron does not intersects with any coordinate plane.");
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
    private boolean isIntersected() {
        return false;
    }

    //todo this
    private boolean lieOnCoordinatePlane() {
        return false;
    }

}


