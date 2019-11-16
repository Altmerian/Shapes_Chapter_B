package by.epam.pavelshakhlovich.shape.inputdata;

import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.factory.ShapeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataObject {

    private List<ShapeType> shapeTypes = new ArrayList<>();
    private List<Point[]> pointsGroups = new ArrayList<>();

    public DataObject() {
    }

    public List<ShapeType> getShapeTypes() {
        return shapeTypes;
    }

    public List<Point[]> getPointsGroups() {
        return pointsGroups;
    }

    public DataObject(List<ShapeType> shapeTypes, List<Point[]> pointsGroups) {
        this.shapeTypes = shapeTypes;
        this.pointsGroups = pointsGroups;
    }

    public void addPoints(Point[] points) {
        this.pointsGroups.add(points);
    }

    public void addShape(ShapeType type) {
        shapeTypes.add(type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DataObject)) return false;

        DataObject dataObject = (DataObject) obj;

        if (!shapeTypes.equals(dataObject.shapeTypes)) return false;
        return Arrays.deepEquals(pointsGroups.toArray(), (dataObject.pointsGroups.toArray()));
    }

    @Override
    public int hashCode() {
        int result = shapeTypes.hashCode();
        result = 31 * result + pointsGroups.hashCode();
        return result;
    }
}
