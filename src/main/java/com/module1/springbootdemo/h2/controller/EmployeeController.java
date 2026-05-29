package com.module1.springbootdemo.h2.controller;

import com.module1.springbootdemo.h2.dto.EmployeeDto;
import com.module1.springbootdemo.h2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/getUser/{usrId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long usrId) {
        return ResponseEntity.ok().body(employeeService.getEmployee(usrId));
    }

    @PostMapping(path = "/postUser")
    public ResponseEntity<EmployeeDto> postEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.postEmployee(employeeDto),  HttpStatus.CREATED);
    }

    @GetMapping(path = "/allUsers")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok().body(employeeService.getEmployees());
    }

    @PutMapping(path = "/updateUser")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto),  HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteUser/{usrId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long usrId) {

        employeeService.deleteEmployee(usrId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/updateUserByPatch/{usrId}")
    public ResponseEntity<EmployeeDto> updateEmployeeByPatch(@RequestBody Map<String, Object> updates, @PathVariable Long usrId) {

        return ResponseEntity.ok().body(employeeService.updatePartialEmployee(updates, usrId));
    }
}
