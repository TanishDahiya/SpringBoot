package com.module1.springbootdemo.h4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostAuditDto {

    private Long id;
    private Integer rev;
    private Integer revType;
    private String title;
    private String createdBy;
    private String modifiedBy;
}