package by.epam.pavelshakhlovich.shape.inputdata;

import by.epam.pavelshakhlovich.shape.entity.EmptyDataException;
import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.factory.ShapeType;
import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    private static Logger logger = LogManager.getLogger();

    @NotNull
    public DataObject parseData(List<String> lines) {
        DataObject data = new DataObject();
        for (String line : lines) {
            Optional<ShapeType> type = parseShapeType(line);
            if (type.isPresent()) {
                Point[] points = parsePoints(line, type.get());
                data.addShape(type.get());
                data.addPoints(points);
                logger.printf(Level.INFO, "parsing result from line %d -\n %s %s",
                        (lines.indexOf(line) + 1), type.get(), Arrays.toString(points));
            } else {
                logger.warn("incorrect data in line " + (lines.indexOf(line) + 1));
            }
        }
        if (data.getShapeTypes().isEmpty()) {
            throw logger.throwing(Level.ERROR,
                    new EmptyDataException ("There are no valid data lines in the source file!"));
        } else {
            return data;
        }
    }

    @VisibleForTesting
    Optional<ShapeType> parseShapeType(String line) {
        for (ShapeType shapeType : ShapeType.values()) {
            if (line.matches(shapeType.getRegex())) {
                return Optional.of(shapeType);
            }
        }
        return Optional.empty();
    }

    @VisibleForTesting
    Point[] parsePoints(String line, ShapeType shapeType) {
        Pattern pattern = Pattern.compile(shapeType.getRegex());
        Matcher matcher = pattern.matcher(line);
        String src = "";
        if (matcher.matches()) {
            src = matcher.group(2);
        }
        String[] source = src.trim().split("[;\\s]+");
        Point[] points = new Point[shapeType.getPointsQuantity()];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(
                    Double.parseDouble(source[i * 3]),
                    Double.parseDouble(source[i * 3 + 1]),
                    Double.parseDouble(source[i * 3 + 2]));
        }
        return points;
    }

}
