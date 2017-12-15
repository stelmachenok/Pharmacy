package by.samsolution.pharmacy.exception;

public class JdbcManipulationException extends PharmacyApplicationException{

    public JdbcManipulationException() {
    }

    public JdbcManipulationException(String message) {
        super(message);
    }

    public JdbcManipulationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcManipulationException(Throwable cause) {
        super(cause);
    }

}
