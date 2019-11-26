package by.epam.pavelshakhlovich.shape.datastorage;

import by.epam.pavelshakhlovich.shape.comporator.ShapeComparator;
import by.epam.pavelshakhlovich.shape.entity.Point;
import by.epam.pavelshakhlovich.shape.entity.Shape;
import by.epam.pavelshakhlovich.shape.observer.Observer;
import by.epam.pavelshakhlovich.shape.observer.Observable;
import by.epam.pavelshakhlovich.shape.factory.ShapeValidator;
import by.epam.pavelshakhlovich.shape.specification.AddingShapesSpecification;
import by.epam.pavelshakhlovich.shape.specification.NameEqualsSpecification;
import by.epam.pavelshakhlovich.shape.specification.PointsEqualsSpecification;
import by.epam.pavelshakhlovich.shape.specification.ShapeFilterSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class Repository implements Observable {
    private static int id;
    private final List<Shape> shapes = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();
    private static Repository instance;
    private static Logger logger = LogManager.getLogger();

    private Repository() {
    }

    public static Repository getInstance() {
        return instance == null ? instance = new Repository() : instance;
    }

    public boolean add(AddingShapesSpecification specification, Shape...incomingShapes) {
        Shape[] shapesToAdd = specification.apply(incomingShapes);
        for (Shape shape : shapesToAdd) {
            if (ShapeValidator.isValid(shape)) {
                shape.setId(++id);
                shapes.add(shape);
                logger.info("shape has been created and added to repository - \n{} ", shape);
                notifyObservers(Event.ADD, shape);
            } else {
                logger.warn("shape is degenerate or it`s impossible to create {} from given points!",
                        shape.getClass().getSimpleName());
                return false;
            }
        }
        return true;
    }

    /**
     * Modify shape in the repository by setting new Points
     *
     * @param shape  shape to update
     * @param points new shape {@link Point} array
     * @return if success updated {@link Shape}, otherwise returns {@code null} if there is no such a shape in the repository
     */
    public Shape update(Shape shape, Point[] points) {
        Shape shapeToUpdate = null;
        if (shapes.contains(shape)) {
            int index = shapes.indexOf(shape);
            shapeToUpdate = shapes.get(index);
            shapeToUpdate.setPoints(points);
            if (ShapeValidator.isValid(shapeToUpdate)) {
                shapes.set(index, shapeToUpdate);
                logger.info("shape has been updated - \n" + shape);
                notifyObservers(Event.UPDATE, shapeToUpdate);
            } else {
                logger.warn("shape is degenerate or its impossible to create {} from given points!",
                        shape.getClass().getSimpleName());
            }
        } else {
            logger.warn("shape hasn't been founded");
        }
        return shapeToUpdate;
    }

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

    public List<Shape> query(ShapeFilterSpecification ...specifications) {
        List<Shape> matchingShapes = shapes;
        for (ShapeFilterSpecification specification : specifications) {
            matchingShapes = matchingShapes.stream()
                    .filter(specification)
                    .collect(Collectors.toList());
        }
        return matchingShapes;
    }

    public boolean contains(Shape incomingShape) {
        NameEqualsSpecification nameSpecification = new NameEqualsSpecification(incomingShape.getClass().getSimpleName());
        PointsEqualsSpecification pointsSpecification = new PointsEqualsSpecification(incomingShape.getPoints());
        List<Shape> matchingShapes = Repository.getInstance().query(nameSpecification, pointsSpecification);
        return !matchingShapes.isEmpty();
    }

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

    @Override
    public void notifyObservers(Event event, Shape shape) {
        for (Observer observer : observers) {
            observer.notify(event, shape);
        }
    }
}
