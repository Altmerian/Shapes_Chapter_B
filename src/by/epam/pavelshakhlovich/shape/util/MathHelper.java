package by.epam.pavelshakhlovich.shape.util;

import by.epam.pavelshakhlovich.shape.entity.Point;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MathHelper {
    public static double getDistance (Point pointA, Point pointB) {
        return sqrt(pow(pointA.getX() - pointB.getX(), 2) +
                pow(pointA.getY() - pointB.getY(), 2) +
                pow(pointA.getZ() - pointB.getZ(), 2));
    }
}
