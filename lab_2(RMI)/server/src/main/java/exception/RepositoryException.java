package exception;

/**
 * <b>Custom exception is the wrapper for all exceptions, that could be thrown in repositories</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class RepositoryException extends Exception {
    /**
     * default constructor
     */
    public RepositoryException() {
    }

    /**
     * constructor with error message to show user
     * @param message error message
     */
    public RepositoryException(String message) {
        super(message);
    }

    /**
     * constructor with error message and stack trace of errors
     * @param message error message
     * @param cause head of stacktrace
     */
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
