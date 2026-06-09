package com.module1.springbootdemo.h3.service;

import com.module1.springbootdemo.h3.dto.StudentRequestDto;
import com.module1.springbootdemo.h3.dto.StudentResponseDto;
import com.module1.springbootdemo.h3.entity.Student;
import com.module1.springbootdemo.h3.repository.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    private final ModelMapper modelMapper;

    public StudentService(StudentRepo studentRepo, ModelMapper modelMapper) {
        this.studentRepo = studentRepo;
        this.modelMapper = modelMapper;
    }


    public StudentResponseDto save(StudentRequestDto studentdto){
        Student student = modelMapper.map(studentdto, Student.class);
        Student savedStudent = studentRepo.save(student);
        StudentResponseDto studentResponseDto = modelMapper.map(savedStudent, StudentResponseDto.class);
        return studentResponseDto;
    }

    public List<StudentResponseDto> getStudentByLastName(String lastName) {
        List<Student> students = studentRepo.findByLastName(lastName);
        List<StudentResponseDto> studentResponseDtos = students.stream()
                .map(
                student -> modelMapper.map(student,StudentResponseDto.class)
                )
                .toList();

        return studentResponseDtos;
    }
}
