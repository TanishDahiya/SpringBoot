package com.module1.springbootdemo.h3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentConcrete {

    private String firstName;
    private String lastName;
    private String email;

}
