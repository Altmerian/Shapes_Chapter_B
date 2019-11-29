package by.epam.pavelshakhlovich.shape.datastorage;

import by.epam.pavelshakhlovich.shape.action.TetrahedronCalculator;
import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.exception.ShapeDataNotFoundException;
import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Warehouse {
    private static Warehouse instance;
    private final List<ShapeData> shapesData = new ArrayList<>();
    private static Logger logger = LogManager.getLogger();

    @VisibleForTesting
    Warehouse() {
    }

    public static Warehouse getInstance() {
        return instance == null ? instance = new Warehouse() : instance;
    }

    public void receiveNotification(Event event, Shape shape) {
        switch (event) {
            case ADD: {
                ShapeData shapeData = new ShapeData(shape);
                Double[] values = calculateData(shape);
                shapeData.setSurfaceArea(values[0]);
                shapeData.setVolume(values[1]);
                shapesData.add(shapeData);
                logger.info("following shape data has been added to the warehouse - \n" + shapeData);
                break;
            }
            case REMOVE: {
                Optional<ShapeData> shapeDataToRemove = shapesData.stream()
                        .filter(shapeData -> shapeData.id == shape.getId())
                        .findAny();
                shapeDataToRemove.ifPresent(shapesData::remove);
                logger.info("following shape data has been removed from the warehouse - \n" +
                        shapeDataToRemove.get());
                break;
            }
            case UPDATE: {
                Optional<ShapeData> shapeDataOptional = shapesData.stream()
                        .filter(shapeData -> shapeData.id == shape.getId())
                        .findAny();
                if (shapeDataOptional.isPresent()) {
                    ShapeData shapeDataToUpdate = shapeDataOptional.get();
                    int index = shapesData.indexOf(shapeDataToUpdate);
                    shapeDataToUpdate.setPoints(shape.getPoints());
                    Double[] values = calculateData(shape);
                    shapeDataToUpdate.setSurfaceArea(values[0]);
                    shapeDataToUpdate.setVolume(values[1]);
                    shapesData.set(index, shapeDataToUpdate);
                    logger.info("following shape data has been updated in the warehouse - \n" +
                            shapeDataToUpdate);
                }
                break;
            }
            default:
                throw new EnumConstantNotPresentException(Event.class, event.name());
        }


    }

    @VisibleForTesting
    Double[] calculateData(Shape shape) {
        Double[] values = new Double[2];
        if (shape.getClass() == Tetrahedron.class) {
            TetrahedronCalculator calculator = new TetrahedronCalculator((Tetrahedron) shape);
            values[0] = calculator.calculateSurfaceArea();
            values[1] = calculator.calculateVolume();
        } else {
            throw logger.throwing(Level.ERROR, new IllegalArgumentException("Unknown shape type!"));
        }
        return values;
    }

    public ShapeData getShapeDataById(int id) {
        Optional<ShapeData> shapeDataOptional = shapesData.stream().filter(x -> x.id == id).findFirst();
        if (shapeDataOptional.isPresent()) {
            logger.info("shape data has been founded in the warehouse by ID: {} - \n{}", id, shapeDataOptional.get());
            return shapeDataOptional.get();
        } else {
            throw logger.throwing(Level.WARN, new ShapeDataNotFoundException(
                    "There is no shape data in the warehouse with ID: " + id));
        }
    }

    @VisibleForTesting
    static class ShapeData {
        private int id;
        private String shapeType;
        private Point[] points;
        private double surfaceArea;
        private double volume;

        public ShapeData(Shape shape) {
            this.id = shape.getId();
            this.shapeType = shape.getClass().getSimpleName();
            this.points = shape.getPoints();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof ShapeData)) return false;

            ShapeData shapeData = (ShapeData) obj;

            if (id != shapeData.id) return false;
            if (Double.compare(shapeData.surfaceArea, surfaceArea) != 0) return false;
            if (Double.compare(shapeData.volume, volume) != 0) return false;
            if (!shapeType.equals(shapeData.shapeType)) return false;
            return Arrays.deepEquals(points, shapeData.points);
        }

        @Override
        public int hashCode() {
            int result;
            result = id;
            result = 31 * result + shapeType.hashCode();
            result = 31 * result + Arrays.hashCode(points);
            result = 31 * result + Double.hashCode(surfaceArea);
            result = 31 * result + Double.hashCode(volume);
            return 31 * result;
        }

        @Override
        public String toString() {
            return String.format("{id=%d, shapeType='%s', points=%s, surfaceArea=%.3f, volume=%.3f}",
                    id, shapeType, Arrays.toString(points), surfaceArea, volume);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
