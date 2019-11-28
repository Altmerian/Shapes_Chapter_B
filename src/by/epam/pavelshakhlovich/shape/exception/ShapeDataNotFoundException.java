package by.epam.pavelshakhlovich.shape.exception;

public class ShapeDataNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -6538065206479547830L;

    public ShapeDataNotFoundException(String message) {
        super(message);
    }
}
