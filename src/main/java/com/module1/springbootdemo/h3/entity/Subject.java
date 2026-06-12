package com.module1.springbootdemo.h3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;
    private String subjectName;
    private String subjectType;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

}
