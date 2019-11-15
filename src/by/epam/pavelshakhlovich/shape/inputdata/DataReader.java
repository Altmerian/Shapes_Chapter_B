package by.epam.pavelshakhlovich.shape.inputdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public List<String> readLinesFromFile(Path path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }



}
