package com.ninjaone.dundie_awards.controller;

import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import java.lang.module.ResolutionException;
import java.util.NoSuchElementException;
import org.hibernate.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
    return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NonUniqueResultException.class)
  public ResponseEntity<?> handleNonUniqueResultException(NonUniqueResultException ex, WebRequest request) {
    return new ResponseEntity<>("Data inconsistency", HttpStatus.NOT_FOUND);
  }

}
