package com.module1.springbootdemo.h4.clients;

import com.module1.springbootdemo.h2.dto.EmployeeDto;
import com.module1.springbootdemo.h4.dto.EmployeeDtoRestClient;
import com.module1.springbootdemo.h4.dto.EmployeeResponseDtoCliennt;

import java.util.List;

public interface EmployeeClient {

    public EmployeeDtoRestClient getEmployee(Long id);
    public EmployeeDtoRestClient deleteEmployee(Long id);
    public EmployeeDtoRestClient updateEmployee(EmployeeDtoRestClient employee);
    public List<EmployeeDtoRestClient> getAllEmployees();

}
