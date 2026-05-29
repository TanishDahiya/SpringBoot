package com.module1.springbootdemo.h2.advices;

import com.module1.springbootdemo.h2.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiAdvice> handleInternalServerError(Exception exception){
        ApiAdvice apiAdvice = ApiAdvice.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiAdvice);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiAdvice> handleInputValidationError(MethodArgumentNotValidException exception ){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiAdvice apiAdvice = ApiAdvice
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input Validation Failed")
                .subErrors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiAdvice);
    }

}
