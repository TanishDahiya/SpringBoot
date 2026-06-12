package com.module1.springbootdemo.h3.controller;

import com.module1.springbootdemo.h3.dto.*;
import com.module1.springbootdemo.h3.entity.AdmissionRecord;
import com.module1.springbootdemo.h3.service.StudentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/addStudent")
    public ResponseEntity<StudentResponseDto> addStudent(@RequestBody StudentRequestDto student) {
        StudentResponseDto savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/getStudentByLastName/{lastName}")
    public ResponseEntity<List<StudentResponseDto>> getStudentByLastName(@PathVariable String lastName){
        List<StudentResponseDto> responseDtos =  studentService.getStudentByLastName(lastName);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/getByFirstNameMatchWithString/{firstName}")
    public ResponseEntity<List<StudentResponseDto>> getByFirstNameMatchWithString(@PathVariable String firstName){
        List<StudentResponseDto> students  = studentService.getByFirstNameMatchWithString(firstName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

//    @GetMapping("/getAllSortByAaadharNumber")
//    public ResponseEntity<List<StudentResponseDto>> findAllSortByAadharNumber(@RequestParam(defaultValue = "id") String sortBy){
//        List<StudentResponseDto> students = studentService.findAllSortByAadharNumber(sortBy);
//        return new ResponseEntity<>(students,HttpStatus.OK);
//    }


    @GetMapping("/getAllSortByAaadharNumber")
    public ResponseEntity<List<StudentResponseDto>> findAllSortByAadharNumber(
            @PageableDefault(
                    page = 0,
//                    size = 2, - this needs to be avoid this will cause API to slow because of large data fetch
                    sort = "studentId",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable
    ){
        List<StudentResponseDto> students = studentService.findAllSortByAadharNumber(pageable);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }


    @GetMapping("/allStudentProjection")
    public ResponseEntity<List<StudentResponseDto>> findAllStudentProjection(){
        List<StudentResponseDto> students = studentService.findAllStudentProjection();
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    // Transaction and persistence context
    @GetMapping("/getStudentById/{id}")
    public void getStudentById(@PathVariable Long id){
        studentService.getStudentById(id);
    }

    //Assuming student is already in DB
    @PostMapping("admissionRecord/{id}")
    public ResponseEntity<AdmissionRecordResponseDto> newAdmissionStudentRecord(@RequestBody AdmissionRecord admissionRecord,@PathVariable Long id){
        AdmissionRecordResponseDto responseDto = studentService.newAdmissionStudentRecord(admissionRecord,id);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/studentAdmission")
    public ResponseEntity<StudentAdmissionResposneDto> newStudentAdmission(@RequestBody StudentAdmissionRequestDto studentAdmissionRequestDto){
        StudentAdmissionResposneDto studentResponseDto = studentService.studentNewAdmission(studentAdmissionRequestDto);
        return new ResponseEntity<>(studentResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

}
