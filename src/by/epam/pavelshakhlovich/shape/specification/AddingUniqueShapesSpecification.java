package by.epam.pavelshakhlovich.shape.specification;

import by.epam.pavelshakhlovich.shape.datastorage.Repository;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AddingUniqueShapesSpecification implements AddingShapesSpecification {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Shape[] apply(Shape...incomingShapes) {
        List<Shape> shapesToAdd = new ArrayList<>();
        for (Shape incomingShape : incomingShapes) {
            if (Repository.getInstance().contains(incomingShape)) {

                logger.warn("shape won't add, there is an equal shape in the repository: \n{}", incomingShape);
            } else {
                shapesToAdd.add(incomingShape);
            }
        }
        return shapesToAdd.toArray(new Shape[1]);
    }
}
