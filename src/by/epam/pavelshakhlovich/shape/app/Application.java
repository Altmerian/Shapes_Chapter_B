package by.epam.pavelshakhlovich.shape.app;

import by.epam.pavelshakhlovich.shape.inputdata.DataParser;
import by.epam.pavelshakhlovich.shape.inputdata.DataReader;
import by.epam.pavelshakhlovich.shape.inputdata.ValidData;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    public static void implementLogic() {
        Path path = Paths.get("data/data.txt");
        List<String> stringData = new DataReader().readLinesFromFile(path);
        ValidData validData = new DataParser().parseData(stringData);
        for (int i = 0; i < validData.getShapeNames().size(); i++) {

        }
    }
}
