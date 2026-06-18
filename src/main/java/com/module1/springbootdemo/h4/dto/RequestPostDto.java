package com.module1.springbootdemo.h4.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestPostDto {

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 500)
    private String description;
}
