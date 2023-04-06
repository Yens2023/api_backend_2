package api.java.backend.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameAlreadyExistsExceptionV1 extends ResponseStatusException {
    public UsernameAlreadyExistsExceptionV1(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
