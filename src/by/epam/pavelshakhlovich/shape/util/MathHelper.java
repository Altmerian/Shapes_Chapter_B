package by.epam.pavelshakhlovich.shape.util;

import by.epam.pavelshakhlovich.shape.entity.Point;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MathHelper {
    public static double distance(Point pointA, Point pointB) {
        return sqrt(pow(pointA.getX() - pointB.getX(), 2) +
                pow(pointA.getY() - pointB.getY(), 2) +
                pow(pointA.getZ() - pointB.getZ(), 2));
    }

    public static double determinant(Point[] points) {
        double x1 = points[1].getX() - points[0].getX();
        double x2 = points[2].getX() - points[0].getX();
        double x3 = points[3].getX() - points[0].getX();
        double y1 = points[1].getY() - points[0].getY();
        double y2 = points[2].getY() - points[0].getY();
        double y3 = points[3].getY() - points[0].getY();
        double z1 = points[1].getZ() - points[0].getZ();
        double z2 = points[2].getZ() - points[0].getZ();
        double z3 = points[3].getZ() - points[0].getZ();
        return x1 * y2 * z3 + y1 * z2 * x3 + x2 * y3 * z1 - x3 * y2 * z1 - y1 * x2 * z3 - y3 * z2 * x1;
    }

    public static int findUniqueNumberIndex(double[] numbers) {
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            int counter = 0;
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    counter++;
                }
            }
            if (counter == 1) {
                index = i;
            }
        }
        return index;
    }

}
