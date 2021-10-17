package by.kozlov.epam.myproject.di.exception;

public class ServiceCreatorException extends Exception{
    public ServiceCreatorException() {
        super();
    }

    public ServiceCreatorException(String message) {
        super(message);
    }

    public ServiceCreatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceCreatorException(Throwable cause) {
        super(cause);
    }
}
