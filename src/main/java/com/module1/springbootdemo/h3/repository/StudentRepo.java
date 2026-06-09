package com.module1.springbootdemo.h3.repository;

import com.module1.springbootdemo.h3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    public List<Student> findByLastName(String lastName);;
}
