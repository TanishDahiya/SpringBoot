package com.module1.springbootdemo.h3.service;

import com.module1.springbootdemo.h3.dto.*;
import com.module1.springbootdemo.h3.entity.AdmissionRecord;
import com.module1.springbootdemo.h3.entity.Student;
import com.module1.springbootdemo.h3.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final int PAGE_SIZE = 2;

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

    public List<StudentResponseDto> getByFirstNameMatchWithString(String firstName) {

        List<Student> students = studentRepo.findByFirstNameMatchWithString(firstName);
        List<StudentResponseDto> studentDto = students.stream()
                .map(student -> modelMapper.map(student, StudentResponseDto.class))
                .toList();
        return studentDto;

    }

//    public List<StudentResponseDto> findAllSortByAadharNumber(String sortBy) {
//        List<Student> students = studentRepo.findAll(Sort.by(Sort.Direction.DESC,sortBy));
//        List<StudentResponseDto> studentsDto = students.stream()
//                .map(student -> modelMapper.map(student,StudentResponseDto.class))
//                .toList();
//        return studentsDto;
//    }


    public List<StudentResponseDto> findAllSortByAadharNumber(Pageable pageable) {
        Pageable pages = PageRequest.of(
                pageable.getPageNumber(),
                PAGE_SIZE,
//                    Sort.by(Sort.Direction.DESC, "aadharNumber") - if we can pass from URL then always it can sort base on AADHAR
                pageable.getSort()
        );
        List<Student> students = studentRepo.findAll(pages).getContent();
        List<StudentResponseDto> studentDto = students.stream()
                .map(student -> modelMapper.map(student,StudentResponseDto.class))
                .toList();

        return studentDto;

    }

    // This projection method is using with Concrete class

//    public List<StudentResponseDto> findAllStudentProjection() {
//        List<StudentConcrete> students = studentRepo.findAllStudent();
//        List<StudentResponseDto> studentResponseDtos = students.stream()
//                .map(student -> modelMapper.map(student,StudentResponseDto.class))
//                .toList();
//
//        return studentResponseDtos;
//
//    }

    //This projection method is using with Interface

    public List<StudentResponseDto> findAllStudentProjection() {
        List<StudentProjectionInterface> students = studentRepo.findAllStudent();
        List<StudentResponseDto> studentResponseDtos = students.stream()
                .map(student -> modelMapper.map(student,StudentResponseDto.class))
                .toList();
        return studentResponseDtos;
    }

    @Transactional
    public void getStudentById(Long id) {
        Student student1 = studentRepo.findById(id).get();
        Student student2 = studentRepo.findById(id).get();
        System.out.print(student1 == student2);

        // So this value updated because it is dirtied, that means it is updated in the persistence context
        //so when this transaction closed, means this method completed then persistence context syncs with the DB
        // that is having dirtied values then this will update to DB
        student1.setFirstName("UpdatedName");
    }

    //Assuming student is already in DB
    @Transactional
    public AdmissionRecordResponseDto newAdmissionStudentRecord(AdmissionRecord admissionRecord, Long id) {

        Student student = studentRepo.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"+ id ));

        //dirtied
        student.setAdmissionRecord(admissionRecord);
        admissionRecord.setStudent(student);

        AdmissionRecordResponseDto admissionRecordResponseDto = modelMapper.map(admissionRecord, AdmissionRecordResponseDto.class);

        return admissionRecordResponseDto;

    }

    @Transactional
    public StudentAdmissionResposneDto studentNewAdmission(StudentAdmissionRequestDto studentAdmissionRequestDto) {
        Student student = new Student();
        student.setFirstName(studentAdmissionRequestDto.getFirstName());
        student.setLastName(studentAdmissionRequestDto.getLastName());
        student.setEmail(studentAdmissionRequestDto.getEmail());
        student.setAadharNumber(studentAdmissionRequestDto.getAadharNumber());
        AdmissionRecord admissionRecord = new AdmissionRecord();
        admissionRecord.setAdmissionNumber(studentAdmissionRequestDto.getAdmissionNumber());
        admissionRecord.setAdmissionStatus(studentAdmissionRequestDto.isAdmissionStatus());

        student.setAdmissionRecord(admissionRecord);
        admissionRecord.setStudent(student);

        studentRepo.save(student);

        return modelMapper.map(admissionRecord, StudentAdmissionResposneDto.class);
    }
}
