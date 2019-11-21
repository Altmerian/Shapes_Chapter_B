package by.epam.pavelshakhlovich.shape.entity;

public interface Observer {

    void update();
    void update(int id, Point[] points);

}
