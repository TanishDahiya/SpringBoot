package com.module1.springbootdemo.h3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admissionRecordId;
    private String admissionNumber;
    private boolean admissionStatus = true;


    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Student student;
}
