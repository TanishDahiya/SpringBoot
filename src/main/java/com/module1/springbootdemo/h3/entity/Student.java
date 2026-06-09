package com.module1.springbootdemo.h3.entity;

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

    // ManyToMany: Student <-> Professor (owning side)
//    // Join table: student_professors
//    @ManyToMany
//    @JoinTable(
//            name = "student_professors",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "professor_id")
//    )
//    private List<Professor> professors;
//
//    // ManyToMany: Student <-> Subject (owning side)
//    // Join table: student_subjects
//    @ManyToMany
//    @JoinTable(
//            name = "student_subjects",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id")
//    )
//    private List<Subject> subjects;
//
//    // OneToOne: Student <-> AdmissionRecord (inverse side)
//    // AdmissionRecord owns the relationship with @JoinColumn
//    @OneToOne(mappedBy = "student")
//    private AdmissionRecord admissionRecord;

}
