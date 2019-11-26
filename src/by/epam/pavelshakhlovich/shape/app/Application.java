package by.epam.pavelshakhlovich.shape.app;

import by.epam.pavelshakhlovich.shape.datastorage.Repository;
import by.epam.pavelshakhlovich.shape.datastorage.Warehouse;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.factory.ShapeFactory;
import by.epam.pavelshakhlovich.shape.inputdata.DataParser;
import by.epam.pavelshakhlovich.shape.inputdata.DataReader;
import by.epam.pavelshakhlovich.shape.inputdata.ParsedData;
import by.epam.pavelshakhlovich.shape.observer.RepositoryWatcher;
import by.epam.pavelshakhlovich.shape.specification.AddingShapesSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.traceEntry();
        RepositoryWatcher watcher = new RepositoryWatcher();
        Repository.getInstance().subscribe(watcher);
        watcher.attachToWarehouse(Warehouse.getInstance());
        Path path = Paths.get("data/data.txt");
        List<String> stringData = new DataReader().readLinesFromFile(path);
        ParsedData parsedData = new DataParser().parseData(stringData);
        ShapeFactory factory = new ShapeFactory();
        for (int i = 0; i < parsedData.getShapeTypes().size(); i++) {
            Shape shape = factory.createShape(
                    parsedData.getShapeTypes().get(i), parsedData.getPointsGroups().get(i));
            Repository.getInstance().add(new AddingShapesSpecification(), shape);
        }
        logger.traceExit();
    }


}

