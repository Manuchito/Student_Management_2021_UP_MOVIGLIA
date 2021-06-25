package Exceptions;

public class DAOClaveDuplicadaException extends Exception {
    public DAOClaveDuplicadaException() {
    }

    public DAOClaveDuplicadaException(String message) {
        super(message);

    }

    public DAOClaveDuplicadaException(String message, Throwable cause) {
        super(message, cause);

    }

    public DAOClaveDuplicadaException(Throwable cause) {
        super(cause);

    }
}
