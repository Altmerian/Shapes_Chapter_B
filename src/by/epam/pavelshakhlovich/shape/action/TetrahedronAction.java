package by.epam.pavelshakhlovich.shape.action;

import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.entity.Tetrahedron;
import by.epam.pavelshakhlovich.shape.factory.Creator;
import by.epam.pavelshakhlovich.shape.factory.TetrahedronCreator;
import by.epam.pavelshakhlovich.shape.inputdata.DataParser;
import by.epam.pavelshakhlovich.shape.inputdata.DataReader;
import by.epam.pavelshakhlovich.shape.entity.FourPoints;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TetrahedronAction {

    public static void doAction() {
        Path path = Paths.get("data/data.txt");
        List<String> stringData = new DataReader().readLinesFromFile(path);
        List<FourPoints> validData = new DataParser().parseData(stringData);
        Creator creator = new TetrahedronCreator();
        for (FourPoints points : validData) {
            Shape tetrahedron = creator.createShape(points.getPoints());
            TetrahedronCalc calculator = new TetrahedronCalc((Tetrahedron) tetrahedron);
            double area = calculator.calculateSurfaceArea();


        }

    }

}
