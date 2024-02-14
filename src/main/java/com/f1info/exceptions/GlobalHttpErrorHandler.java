package com.f1info.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("Can't find user by given id.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HistoryNotFoundException.class)
    public ResponseEntity<Object> handleHistoryNotFoundException(HistoryNotFoundException exception) {
        return new ResponseEntity<>("Can't find history data by given id.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<Object> handleUserDuplicateException(UserDuplicateException e) {
        return new ResponseEntity<>("Given login already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RaceNotFoundException .class)
    public ResponseEntity<Object> handleRaceNotFoundException (RaceNotFoundException  e) {
        return new ResponseEntity<>("Can't find face by given data.", HttpStatus.NOT_FOUND);
    }
}