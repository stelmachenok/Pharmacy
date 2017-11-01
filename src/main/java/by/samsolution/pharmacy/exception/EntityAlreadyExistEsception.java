package by.samsolution.pharmacy.exception;

public class EntityAlreadyExistEsception extends PharmacyApplicationException {
    public EntityAlreadyExistEsception() {
        super();
    }

    public EntityAlreadyExistEsception(String message) {
        super(message);
    }

    public EntityAlreadyExistEsception(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyExistEsception(Throwable cause) {
        super(cause);
    }
}
