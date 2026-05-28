package com.module1.springbootdemo.h2.repository;

import com.module1.springbootdemo.h2.dto.EmployeeDto;
import com.module1.springbootdemo.h2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
