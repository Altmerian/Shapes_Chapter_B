package by.epam.pavelshakhlovich.shape.observer;

import by.epam.pavelshakhlovich.shape.action.TetrahedronCalculator;
import by.epam.pavelshakhlovich.shape.datastorage.Event;
import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;

import java.util.HashMap;
import java.util.Map;

public class Warehouse implements Observer {
    private static Warehouse instance;
    private Map<Integer, ShapeData> shapesData = new HashMap<>();

    private Warehouse() {}

    public static Warehouse getInstance() {
        return instance == null ? instance = new Warehouse() : instance;
    }

    @Override
    public void update(Event event, int id, Shape shape) {
        switch (event) {
            case ADD: {
                ShapeData shapeData = new ShapeData(shape.getRuntimeType(), shape.getPoints());
                Double[] values = calculateData(shape);
                shapeData.setSurfaceArea(values[0]);
                shapeData.setVolume(values[1]);
                shapesData.put(id, shapeData);

            }
            case REMOVE:{

            }
            case UPDATE: {
                ShapeData shapeData = shapesData.get(id);
                shapeData.setPoints(shape.getPoints());
                Double[] values = calculateData(shape);
                shapeData.setSurfaceArea(values[0]);
                shapeData.setVolume(values[1]);
            }
        }


    }

    private Double[] calculateData(Shape shape) {
        Double[] values = new Double[2];
        if (shape.getClass() == Tetrahedron.class) {
            TetrahedronCalculator calculator = new TetrahedronCalculator((Tetrahedron) shape);
            values[0] = calculator.calculateSurfaceArea();
            values[1] = calculator.calculateVolume();
        } else {
            throw new IllegalArgumentException("Unknown shape type!");
        }
        return values;
    }

    private static class ShapeData {
        private String shapeType;
        private Point[] points;
        private double surfaceArea;
        private double volume;

        public ShapeData(String shapeType, Point[] points) {
            this.shapeType = shapeType;
            this.points = points;
        }

        public String getShapeType() {
            return shapeType;
        }

        public void setShapeType(String shapeType) {
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
