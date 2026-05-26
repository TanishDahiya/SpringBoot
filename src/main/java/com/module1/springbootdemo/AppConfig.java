package com.module1.springbootdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public PayemntService PayemntService() {
        System.out.println(" Modified PayemntService");
        return new PayemntService();
    }
}
