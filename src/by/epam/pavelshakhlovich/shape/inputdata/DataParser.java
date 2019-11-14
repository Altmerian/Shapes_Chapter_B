package by.epam.pavelshakhlovich.shape.inputdata;

import by.epam.pavelshakhlovich.shape.entity.Point;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public List<FourPoints> parseData (List<String> lines) {
        List<FourPoints> data = new ArrayList<>();
        for (String line : lines) {
            if (line.matches("(\\d([,.]\\d*)*){12}")) {
                FourPoints points = new FourPoints(this.parsePoints(line));
                data.add(points);
            } else {
                throw new IllegalArgumentException("Incorrect data in line " + lines.indexOf(line));
            }
        }
        return data;
    }

    private Point[] parsePoints (String line) {
        String [] source = line.trim().split("(?<=[\\s;])");
        Point[] points = new Point[4];

        points[0] = new Point(
                Double.parseDouble(source[0]),
                Double.parseDouble(source[1]),
                Double.parseDouble(source[2]));
        points[1] = new Point(
                Double.parseDouble(source[3]),
                Double.parseDouble(source[4]),
                Double.parseDouble(source[5]));
        points[2] = new Point(
                Double.parseDouble(source[6]),
                Double.parseDouble(source[7]),
                Double.parseDouble(source[8]));
        points[3] = new Point(
                Double.parseDouble(source[9]),
                Double.parseDouble(source[10]),
                Double.parseDouble(source[11]));
        return points;
    }

}
