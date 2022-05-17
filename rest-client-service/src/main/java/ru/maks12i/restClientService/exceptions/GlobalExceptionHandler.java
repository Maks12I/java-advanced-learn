package ru.maks12i.restClientService.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        return ResponseEntity.badRequest().body(
                fieldErrors.stream().collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList()
                        )
                ))
        );
    }

    @ExceptionHandler({ClientAlreadyExistsException.class})
    public ResponseEntity<ExceptionResponse> handleBadRequestException(ClientAlreadyExistsException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ExceptionResponse> handleValidationException(ConstraintViolationException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({ClientNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleValidationException(ClientNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DeleteDateLessException.class})
    public ResponseEntity<ExceptionResponse> handleValidationException(DeleteDateLessException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
