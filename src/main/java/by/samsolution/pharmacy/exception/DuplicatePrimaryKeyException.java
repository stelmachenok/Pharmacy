package by.samsolution.pharmacy.exception;

public class DuplicatePrimaryKeyException extends PharmacyApplicationException{
    public DuplicatePrimaryKeyException() {
    }

    public DuplicatePrimaryKeyException(String message) {
        super(message);
    }

    public DuplicatePrimaryKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatePrimaryKeyException(Throwable cause) {
        super(cause);
    }
}
