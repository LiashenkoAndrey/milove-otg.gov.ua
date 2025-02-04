package gov.milove.main.exception;

public class UtilException extends RuntimeException {

    public UtilException() {
        super();
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }
}
