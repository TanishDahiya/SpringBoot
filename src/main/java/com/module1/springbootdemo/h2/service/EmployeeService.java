package com.module1.springbootdemo.h2.service;

import com.module1.springbootdemo.h2.dto.EmployeeDto;
import com.module1.springbootdemo.h2.entity.Employee;
import com.module1.springbootdemo.h2.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository,  ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this .modelMapper = modelMapper;
    }

    public EmployeeDto getEmployee(Long id){
        Employee employee =  employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        EmployeeDto employeeDto =  modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    public EmployeeDto postEmployee(EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee =  employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public List<EmployeeDto> getEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
                .toList();
    }

    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public void deleteEmployee(Long id){
         employeeRepository.deleteById(id);
    }

    public EmployeeDto updatePartialEmployee(Map<String, Object> updates,Long usrId) {

        boolean exists = employeeRepository.existsById(usrId);

        if (!exists) return null;

        Employee employee = employeeRepository.findById(usrId).get();

        updates.forEach((field, value) -> {

            Field fieldtobeUpdate = ReflectionUtils.findField(Employee.class, field);

            fieldtobeUpdate.setAccessible(true);

            ReflectionUtils.setField(
                    fieldtobeUpdate,
                    employee,
                    value);
        });

        return modelMapper.map(
                employeeRepository.save(employee),
                EmployeeDto.class);
    }
}
