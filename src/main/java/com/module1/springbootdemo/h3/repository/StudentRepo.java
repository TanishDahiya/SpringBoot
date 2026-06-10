package com.module1.springbootdemo.h3.repository;

import com.module1.springbootdemo.h3.dto.StudentConcrete;
import com.module1.springbootdemo.h3.dto.StudentProjectionInterface;
import com.module1.springbootdemo.h3.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    List<Student> findAll(Sort sort);

    Page<Student> findAll(Pageable pageable);

    // This is using concrete class
//    @Query("""
//        select new com.module1.springbootdemo.h3.dto.StudentConcrete(
//            e.firstName,
//            e.lastName,
//            e.email
//        ) from Student e
//    """)
//List<StudentConcrete> findAllStudent();

    //This is using an Interface
    @Query("""
       select
       e.firstName as firstName,
       e.lastName as lastName,
       e.email as email
       from Student e
       """)
    List<StudentProjectionInterface> findAllStudent();

}
