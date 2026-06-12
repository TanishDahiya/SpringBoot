package com.module1.springbootdemo.h3.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAdmissionRequestDto {


    private String firstName;
    private String lastName;
    private String email;
    @Column(unique = true)
    private String aadharNumber;
    private String admissionNumber;
    private boolean admissionStatus = true;

}
