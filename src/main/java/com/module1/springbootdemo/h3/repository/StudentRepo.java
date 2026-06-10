package com.module1.springbootdemo.h3.repository;

import com.module1.springbootdemo.h3.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    List<Student> findByLastName(String lastName);

    @Query("select e from Student e where e.firstName like CONCAT(:firstName, '%')")
    List<Student> findByFirstNameMatchWithString(String firstName);

    List<Student> findAll(Sort sort);

}
