package com.api.primeos.exception;

import com.api.primeos.exception.ClienteNotFoundException;
import com.api.primeos.exception.FuncionarioNotFoundException;
import com.api.primeos.exception.DuplicateLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Exceção personalizada quando o cliente não é encontrado
    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> handleClienteNotFoundException(ClienteNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Exceção personalizada quando o funcionário não é encontrado
    @ExceptionHandler(FuncionarioNotFoundException.class)
    public ResponseEntity<String> handleFuncionarioNotFoundException(FuncionarioNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Exceção personalizada para quando há login duplicado
    @ExceptionHandler(DuplicateLoginException.class)
    public ResponseEntity<String> handleDuplicateLoginException(DuplicateLoginException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Exceção genérica para quando há problemas inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Ocorreu um erro inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Exceção para quando campos obrigatórios não são preenchidos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
