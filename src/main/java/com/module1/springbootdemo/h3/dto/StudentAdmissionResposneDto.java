package com.module1.springbootdemo.h3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAdmissionResposneDto {
    private String firstName;
    private String lastName;
    private String admissionNumber;
}
