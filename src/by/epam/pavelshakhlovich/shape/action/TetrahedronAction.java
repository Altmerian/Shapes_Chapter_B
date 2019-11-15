package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;

public class TetrahedronAction {
    private Tetrahedron tetrahedron;

    public TetrahedronAction(Tetrahedron tetrahedron) {
        this.tetrahedron = tetrahedron;
    }

    public void doAction() {
            TetrahedronCalculator calculator = new TetrahedronCalculator(tetrahedron);
            double area = calculator.calculateSurfaceArea();
        }
    }


