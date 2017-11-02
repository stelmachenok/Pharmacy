package by.samsolution.pharmacy.exception;

public class ObjectValidationFailedException extends PharmacyApplicationException{
    public ObjectValidationFailedException() {
        super();
    }

    public ObjectValidationFailedException(String message) {
        super(message);
    }

    public ObjectValidationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectValidationFailedException(Throwable cause) {
        super(cause);
    }
}
