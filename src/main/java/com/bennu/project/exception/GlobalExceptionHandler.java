package com.bennu.project.exception;

import cl.bennu.commons.domain.base.Error;
import cl.bennu.commons.exception.NoDataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<Error> handleNoData(NoDataException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidation(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return error(HttpStatus.BAD_REQUEST, mensaje);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Error> handleUnique(DuplicateKeyException ex) {
        return error(HttpStatus.CONFLICT, "El registro ya existe");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Error> handleForeignKey(DataIntegrityViolationException ex) {
        return error(HttpStatus.CONFLICT, "El registro hace referencia a algo que no existe o está en uso");
    }

    private ResponseEntity<Error> error(HttpStatus status, String mensaje) {
        Error error = new Error();
        error.setType(status.getReasonPhrase());
        error.setCode(status.value());
        error.setError(mensaje);
        return ResponseEntity.status(status).body(error);
    }
}