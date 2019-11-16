package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import com.google.common.annotations.VisibleForTesting;

import java.util.HashSet;
import java.util.Set;

public class TetrahedronAction {
    private Tetrahedron tetrahedron;

    public TetrahedronAction(Tetrahedron tetrahedron) {
        this.tetrahedron = tetrahedron;
    }

    public void doAction() {
        if (isDegenerate()) {
            System.out.println("One or more vertexes have the same coordinates.");
            System.out.println("Tetrahedron is degenerate!");
        } else {
            TetrahedronCalculator calculator = new TetrahedronCalculator(tetrahedron);
            double area = calculator.calculateSurfaceArea();
        }
    }

    @VisibleForTesting
    boolean isDegenerate() {
        Set<Point> vertexes = new HashSet<>();
        return vertexes.add(tetrahedron.getVertexA()) &&
                vertexes.add(tetrahedron.getVertexB()) &&
                vertexes.add(tetrahedron.getVertexC()) &&
                vertexes.add(tetrahedron.getVertexD());
    }
}


