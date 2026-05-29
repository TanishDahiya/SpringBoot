package com.module1.springbootdemo.h2.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiAdvice {
    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}
