package by.epam.pavelshakhlovich.shape.inputdata;

import by.epam.pavelshakhlovich.shape.entity.InvalidLineException;
import by.epam.pavelshakhlovich.shape.entity.Point;
import com.google.common.annotations.VisibleForTesting;

import java.util.List;

public class DataParser {

    public ValidData parseData(List<String> lines) {
        ValidData data = new ValidData();
        for (String line : lines) {
            //todo group parsing
            if (isValid(line)) {
                Point[] points = parsePoints(line);
                String name = parseName(line);
                data.addPoints(points);
                data.addName(name);
            } else {
                throw new InvalidLineException("Incorrect data in line " + lines.indexOf(line));
            }
        }
        return data;
    }

    @VisibleForTesting
    boolean isValid(String line) {
        return line.matches("([a-zA-Z]+\\s+)(-?\\d[,.]*\\d*[;\\s]*){12}");
    }

    private String parseName(String line) {
        //todo this
        return null;
    }

    @VisibleForTesting
    Point[] parsePoints (String line) {
        //todo this
        String [] source = line.trim().split("[;\\s]+");
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
