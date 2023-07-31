package com.example.demo.controller;

import com.example.demo.exception.DuplicateUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
@Slf4j
public class DemoControllerAdvice {
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<Object> handleDuplicateUserException(DuplicateUserException ex) {
      log.info(ex.getMessage());
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
