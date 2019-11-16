package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import com.google.common.annotations.VisibleForTesting;

public class TetrahedronAction {
    private Tetrahedron tetrahedron;

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
        return xCause || yCause || zCause;
    }
}


