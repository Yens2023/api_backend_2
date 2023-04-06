package api.java.backend.handler.auth;

import api.java.backend.dto.custom.APIResponse;
import api.java.backend.dto.custom.ErrorDTO;
import api.java.backend.exception.auth.EmailAlreadyExistsException;
import api.java.backend.exception.auth.UsernameAlreadyExistsException;
import api.java.backend.utils.SD;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class AuthServiceExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception){
        APIResponse<?> serviceResponse = new APIResponse<>();
        List<ErrorDTO> errors = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(
                        error ->
                        {
                            ErrorDTO errorDTO = new ErrorDTO(error.getField(), error.getDefaultMessage());
                            errors.add(errorDTO);
                        }
                );
        serviceResponse.setStatus(SD.FAILED);
        serviceResponse.setErrors(errors);
        return serviceResponse;
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public APIResponse<?> handleUsernameAlreadyExists(UsernameAlreadyExistsException exception){
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus(SD.FAILED);
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("Username",exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public APIResponse<?> handleEmailAlreadyExists(EmailAlreadyExistsException exception){
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus(SD.FAILED);
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("Email",exception.getMessage())));
        return serviceResponse;
    }

}
