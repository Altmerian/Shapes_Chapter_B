package by.epam.pavelshakhlovich.shape.entity;

import by.epam.pavelshakhlovich.shape.action.TetrahedronCalculator;
import by.epam.pavelshakhlovich.shape.factory.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Observer {
    private static Warehouse instance;
    private List<ShapeData> shapesData;

    private Warehouse() {}

    public static Warehouse getInstance() {
        return instance == null ? instance = new Warehouse() : instance;
    }


    @Override
    public void update() {

    }

    public void update(int id, Point[] points) {
        ShapeData shapeData = shapesData.get(id);
        shapeData.setPoints(points);
        TetrahedronCalculator calculator= new TetrahedronCalculator(new Tetrahedron(points));
        shapeData.setSurfaceArea(calculator.calculateSurfaceArea());
        shapeData.setVolume(calculator.calculateVolume());
    }

    private class ShapeData {
        private int id;
        private ShapeType shapeType;
        private Point[] points;
        private double surfaceArea;
        private double volume;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ShapeType getShapeType() {
            return shapeType;
        }

        public void setShapeType(ShapeType shapeType) {
            this.shapeType = shapeType;
        }

        public Point[] getPoints() {
            return points;
        }

        public void setPoints(Point[] points) {
            this.points = points;
        }

        public double getSurfaceArea() {
            return surfaceArea;
        }

        public void setSurfaceArea(double surfaceArea) {
            this.surfaceArea = surfaceArea;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }
    }
}
