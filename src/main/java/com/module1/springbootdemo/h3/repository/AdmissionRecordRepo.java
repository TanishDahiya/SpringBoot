package com.module1.springbootdemo.h3.repository;

import com.module1.springbootdemo.h3.entity.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRecordRepo extends JpaRepository<AdmissionRecord,Long> {
}
