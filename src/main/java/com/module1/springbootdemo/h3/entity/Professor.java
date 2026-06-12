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
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professorId;
    private String professorName;


    @OneToMany
    @JoinColumn(name = "professor_id")
    private List<Subject> subjects;

    @ManyToMany(mappedBy = "professors")
    private List<Student> students;

}
