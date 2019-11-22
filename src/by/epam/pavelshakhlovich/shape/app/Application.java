package by.epam.pavelshakhlovich.shape.app;

import by.epam.pavelshakhlovich.shape.datastorage.Repository;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.observer.Warehouse;
import by.epam.pavelshakhlovich.shape.factory.ShapeFactory;
import by.epam.pavelshakhlovich.shape.inputdata.DataObject;
import by.epam.pavelshakhlovich.shape.inputdata.DataParser;
import by.epam.pavelshakhlovich.shape.inputdata.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    private static Logger logger = LogManager.getLogger();

    public static void implementLogic (){
        logger.traceEntry();
        Repository.getInstance().subscribe(Warehouse.getInstance());

        Path path = Paths.get("data/data.txt");
        List<String> stringData = new DataReader().readLinesFromFile(path);
        DataObject dataObject = new DataParser().parseData(stringData);
        ShapeFactory factory = new ShapeFactory();
        for (int i = 0; i < dataObject.getShapeTypes().size(); i++) {
            Shape shape = factory.createShape(
                    dataObject.getShapeTypes().get(i), dataObject.getPointsGroups().get(i));
            System.out.println("\nSHAPE - " + shape);
            shape.becomeChosen();
        }


        logger.traceExit();
    }


}

