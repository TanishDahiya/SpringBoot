package com.module1.springbootdemo.h2.advices;


import com.module1.springbootdemo.h2.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiAdvice> handleEmployeeNotFound(ResourceNotFoundException exception){
       ApiAdvice apiAdvice = ApiAdvice.builder()
               .status(HttpStatus.NOT_FOUND)
               .message(exception.getMessage())
               .build();
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiAdvice);
    }

}
