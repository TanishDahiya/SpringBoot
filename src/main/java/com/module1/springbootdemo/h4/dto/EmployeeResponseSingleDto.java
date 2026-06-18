package com.module1.springbootdemo.h4.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponseSingleDto {

    private String status;
    private EmployeeDtoRestClient data;
    private String message;

}
