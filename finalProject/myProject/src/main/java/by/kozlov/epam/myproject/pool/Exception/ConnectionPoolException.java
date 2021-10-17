package by.kozlov.epam.myproject.pool.Exception;

public class ConnectionPoolException extends Exception{
    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
