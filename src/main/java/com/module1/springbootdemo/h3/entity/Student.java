package com.module1.springbootdemo.h3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.module1.springbootdemo.h3.commonlibs.enums.StudentEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student", indexes = {
        @Index(name = "idx_student_email", columnList = "email")
})
public class Student {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    @Column(unique = true)
    private String aadharNumber;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private StudentEnum isActive = StudentEnum.ACTIVE;;

    @ManyToMany
    @JoinTable(
            name = "student_professor",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professors;

    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL) // Added CASCADED
    @JsonIgnore
    private AdmissionRecord admissionRecord;

}
