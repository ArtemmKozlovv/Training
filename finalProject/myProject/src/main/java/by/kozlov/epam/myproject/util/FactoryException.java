package by.kozlov.epam.myproject.util;

public class FactoryException extends Exception {
    public FactoryException() {}

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(Throwable cause) {
        super(cause);
    }
}
