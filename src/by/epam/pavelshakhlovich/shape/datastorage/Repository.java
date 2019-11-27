package by.epam.pavelshakhlovich.shape.datastorage;

import by.epam.pavelshakhlovich.shape.comporator.ShapeChainedComparator;
import by.epam.pavelshakhlovich.shape.comporator.ShapeComparator;
import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.factory.ShapeValidator;
import by.epam.pavelshakhlovich.shape.observer.Observable;
import by.epam.pavelshakhlovich.shape.observer.Observer;
import by.epam.pavelshakhlovich.shape.specification.AddingShapesSpecification;
import by.epam.pavelshakhlovich.shape.specification.AllShapesIncludedSpecification;
import by.epam.pavelshakhlovich.shape.specification.IdEqualsShapeSpecification;
import by.epam.pavelshakhlovich.shape.specification.ShapeFilterSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Repository implements Observable {
    private static int id;
    private final List<Shape> shapes = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();
    private static Repository instance;
    public static final AllShapesIncludedSpecification ALL_SHAPES_INCLUDED_SPECIFICATION =
            new AllShapesIncludedSpecification();
    private static Logger logger = LogManager.getLogger();

    private Repository() {
    }

    public static Repository getInstance() {
        return instance == null ? instance = new Repository() : instance;
    }

    /**
     * Appends shape(s) to the nested list of repository after applying specification
     * which presents requirements to the input data
     *
     * @param specification  implements {@link java.util.function.Function}
     * @param incomingShapes shape(s) to add
     * @return {@code List<Shape>} of successfully added shapes
     */
    public List<Shape> add(AddingShapesSpecification specification, Shape... incomingShapes) {
        Shape[] shapesToAdd = specification.apply(incomingShapes);
        if (shapesToAdd[0] == null) {
            logger.warn("shape(s) won't add because there is equal shape(s) in the repo already with such points!");
            return Collections.emptyList();
        }
        List<Shape> shapesAdded = new ArrayList<>();
        for (Shape shape : shapesToAdd) {
            if (ShapeValidator.isValid(shape)) {
                shape.setId(++id);
                shapes.add(shape);
                shapesAdded.add(shape);
                logger.info("shape has been created and added to repository - \n{} ", shape);
                notifyObservers(Event.ADD, shape);
            } else {
                logger.warn("shape is degenerate or it`s impossible to create {} from given points!",
                        shape.getClass().getSimpleName());
            }
        }
        return shapesAdded;
    }

    /**
     * Modify shape in the repository by setting new Points for it
     *
     * @param shape     instance of {@link Shape} to update
     * @param newPoints new {@link Point} array
     * @return updated {@link Shape} if success, otherwise returns {@code null}
     * if there is no such a shape in the repository
     */
    public Shape update(Shape shape, Point[] newPoints) {
        Shape shapeToUpdate = null;
        if (shapes.contains(shape)) {
            int index = shapes.indexOf(shape);
            shapeToUpdate = shapes.get(index);
            shapeToUpdate.setPoints(newPoints);
            if (ShapeValidator.isValid(shapeToUpdate)) {
                shapes.set(index, shapeToUpdate);
                logger.info("shape has been updated - \n" + shape);
                notifyObservers(Event.UPDATE, shapeToUpdate);
            } else {
                logger.warn("the shape hasn't been updated cause it would become degenerate or " +
                                "it's impossible to create {} from given points!",
                        shape.getClass().getSimpleName());
            }
        } else {
            logger.warn("shape hasn't been founded");
        }
        return shapeToUpdate;
    }

    /**
     * Modifies shape with the specified {@code Shape#id} in the repository by setting new Points for it
     *
     * @param id        personal number that uniquely identifies shape in the repository
     * @param newPoints new {@link Point} array
     * @return updated {@link Shape} if success, otherwise returns {@code null}
     * if there is no such a shape in the repository
     */
    public Shape update(int id, Point[] newPoints) {
        Shape shapeToUpdate = query(new IdEqualsShapeSpecification(id)).get(0);
        if (shapeToUpdate != null) {
            shapeToUpdate.setPoints(newPoints);
            int index = shapes.indexOf(shapeToUpdate);
            if (ShapeValidator.isValid(shapeToUpdate)) {
                shapes.set(index, shapeToUpdate);
                logger.info("shape has been updated - \n" + shapeToUpdate);
                notifyObservers(Event.UPDATE, shapeToUpdate);
            } else {
                logger.warn("she shape hasn't been updated cause it would become degenerate or " +
                                "it's impossible to create {} from given points!",
                        shapeToUpdate.getClass().getSimpleName());
            }
        } else {
            logger.warn("shape hasn't been founded");
        }
        return shapeToUpdate;
    }

    /**
     * Remove shapes that match given specification
     *
     * @param specification {@link ShapeFilterSpecification} of shapes to remove
     * @return {@code List<Shape>} of removed shapes
     */
    public List<Shape> remove(ShapeFilterSpecification specification) {
        List<Shape> shapesToRemove = this.query(specification);
        if (!shapesToRemove.isEmpty()) {
            for (Shape shape : shapesToRemove) {
                shapes.remove(shape);
                logger.info("shape below has been removed from repository - \n" + shape);
                notifyObservers(Event.REMOVE, shape);
            }
        } else {
            logger.warn("There are no shapes that match criteria in the repository");
        }
        return shapesToRemove;
    }

    /**
     * Makes a query to the repository to receive shapes that match {@link ShapeFilterSpecification}
     *
     * @param specifications single {@link ShapeFilterSpecification} or sequence of them
     * @return {@code List<Shape>} after filtering by all specifications
     */
    public List<Shape> query(ShapeFilterSpecification... specifications) {
        List<Shape> matchingShapes = shapes;
        for (ShapeFilterSpecification specification : specifications) {
            matchingShapes = matchingShapes.stream()
                    .filter(specification)
                    .collect(Collectors.toList());
        }
        return matchingShapes;
    }

    /**
     * Returns <tt>true</tt> if this Repository contains the specified element.
     * More formally, returns <tt>true</tt> if nested list contains
     * at least one  <tt>shape</tt> such that
     * <tt>(incomingShape.equals(shape))</tt>.
     * {@link Shape#equals} method checks all fields instead of <tt>Shape#id</tt>
     * So, shapes are equal, when they have same type and the same Points
     *
     * @param incomingShape element whose presence in this list is to be tested
     * @return <tt>true</tt> if this Repository contains the specified element
     */
    public boolean contains(Shape incomingShape) {
        return shapes.contains(incomingShape);
    }

    /**
     * Sorts shapes in repository by the specified {@link ShapeComparator}
     * or by the chain of comparators
     *
     * @param shapeComparator {@link ShapeComparator} or {@link ShapeChainedComparator}
     */
    public void sort(ShapeComparator shapeComparator) {
        shapes.sort(shapeComparator);
        logger.info("shapes in repository have been sorted by " + shapeComparator.getName());
    }

    @Override
    public void subscribe(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Null Observer");
        } else {
            observers.add(observer);
        }
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies subscribed observers about changing of the stored shapes state
     * @param event kind of the {@link Event}
     * @param shape changed {@link Shape}
     */
    @Override
    public void notifyObservers(Event event, Shape shape) {
        for (Observer observer : observers) {
            observer.notify(event, shape);
        }
    }

    @Override
    public String toString() {
        return "Repository=" + shapes;
    }
}
