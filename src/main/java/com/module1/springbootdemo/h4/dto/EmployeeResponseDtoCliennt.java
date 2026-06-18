package com.module1.springbootdemo.h4.dto;

import com.module1.springbootdemo.h2.dto.EmployeeDto;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponseDtoCliennt {

    private String status;
    private List<EmployeeDtoRestClient> data;
    private String message;

}
