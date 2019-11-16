package by.epam.pavelshakhlovich.shape.app;

import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.factory.ShapesCreator;
import by.epam.pavelshakhlovich.shape.inputdata.DataObject;
import by.epam.pavelshakhlovich.shape.inputdata.DataParser;
import by.epam.pavelshakhlovich.shape.inputdata.DataReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Path path = Paths.get("data/data.txt");
        List<String> stringData = new DataReader().readLinesFromFile(path);
        DataObject dataObject = new DataParser().parseData(stringData);
        ShapesCreator creator = new ShapesCreator();
        for (int i = 0; i < dataObject.getShapeTypes().size(); i++) {
            Shape shape = creator.createShape(
                    dataObject.getShapeTypes().get(i), dataObject.getPointsGroups().get(i));
            System.out.println("SHAPE - " + shape);
            shape.becomeChosen();
        }
    }


}

