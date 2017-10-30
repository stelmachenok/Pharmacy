package by.samsolution.pharmacy.exception;

public class PharmacyApplicationException extends Exception{
    public PharmacyApplicationException() {
        super();
    }

    public PharmacyApplicationException(String message) {
        super(message);
    }

    public PharmacyApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PharmacyApplicationException(Throwable cause) {
        super(cause);
    }
}
