package api.java.backend.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyExistsExceptionV1 extends ResponseStatusException {
    public EmailAlreadyExistsExceptionV1(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
