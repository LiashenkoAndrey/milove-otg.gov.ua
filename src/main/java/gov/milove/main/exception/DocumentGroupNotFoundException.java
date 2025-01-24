package gov.milove.main.exception;

public class DocumentGroupNotFoundException extends RuntimeException{

    public DocumentGroupNotFoundException() {
    }

    public DocumentGroupNotFoundException(String message) {
        super(message);
    }

    public DocumentGroupNotFoundException(Throwable cause) {
        super(cause);
    }
}
