package by.epam.pavelshakhlovich.shape.inputdata;

import by.epam.pavelshakhlovich.shape.entity.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidData {
    private List<String> shapeNames = new ArrayList<>();
    private List<Point> points = new ArrayList<>();

    public ValidData() {
    }

    public List<String> getShapeNames() {
        return shapeNames;
    }

    public List<Point> getPoints() {
        return points;
    }

    public ValidData(List<String> shapeNames, List<Point> points) {
        this.shapeNames = shapeNames;
        this.points = points;
    }

    public void addPoints(Point[] points) {
        this.points.addAll(Arrays.asList(points));
    }

    public void addName(String name) {
        shapeNames.add(name);
    }
}
