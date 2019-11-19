package by.epam.pavelshakhlovich.shape.exception;

public class EmptyDataException extends RuntimeException{
    private static final long serialVersionUID = 4129296561554940980L;

    public EmptyDataException(String message) {
        super(message);
    }
}
