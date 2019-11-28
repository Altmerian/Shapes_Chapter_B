package by.epam.pavelshakhlovich.shape.inputdata;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private static Logger logger = LogManager.getLogger();

    public List<String> readLinesFromFile(Path path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);
            logger.info("data has been read from file \"{}\" ", path);
        } catch (IOException e) {
            logger.catching(Level.ERROR, e);
        }
        return lines;
    }

}
