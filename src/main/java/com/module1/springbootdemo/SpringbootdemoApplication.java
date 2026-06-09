package com.module1.springbootdemo;

import com.module1.springbootdemo.h1.CakeBaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootdemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);


	}
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Spring Boot Process starts ");
    }

}
