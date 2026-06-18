package com.module1.springbootdemo.h4.controller;


import com.module1.springbootdemo.h4.clients.clientImpl.EmployeeClientImpl;
import com.module1.springbootdemo.h4.dto.EmployeeDtoRestClient;
import com.module1.springbootdemo.h4.dto.EmployeeResponseDtoCliennt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping(path = "/employeeRestClient")
@RequiredArgsConstructor
public class EmployeeRestClientController {

    private final EmployeeClientImpl employeeClient;

    @GetMapping("/listOfAllEmployees")
    public ResponseEntity<List<EmployeeDtoRestClient>> getAllEmployees() {
        List<EmployeeDtoRestClient> employeeDtoRestClients = employeeClient.getAllEmployees();
        return new ResponseEntity<>(employeeDtoRestClients, HttpStatus.OK);
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<EmployeeDtoRestClient> getEmployee(@PathVariable Long id) {
        EmployeeDtoRestClient employeeDtoRestClients = employeeClient.getEmployee(id);
        return new ResponseEntity<>(employeeDtoRestClients, HttpStatus.OK);
    }


}
