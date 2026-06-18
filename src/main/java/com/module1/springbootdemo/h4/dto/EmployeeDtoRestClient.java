package com.module1.springbootdemo.h4.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDtoRestClient {

    private Long id;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("employee_salary")
    private Integer employeeSalary;

    @JsonProperty("employee_age")
    private Integer employeeAge;

}
