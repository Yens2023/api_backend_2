package api.java.backend.exception.auth;

public class EmailAlreadyExistsException  extends RuntimeException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
