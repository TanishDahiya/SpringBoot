package com.module1.springbootdemo;

import com.module1.springbootdemo.h1.CakeBaker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootdemoApplication implements CommandLineRunner {

//    @Autowired
//    private PayemntService payemntService;
    private CakeBaker cakeBaker;

    public SpringbootdemoApplication(CakeBaker cakeBaker) {
        this.cakeBaker = cakeBaker;
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);


	}
    @Override
    public void run(String... args) throws Exception {
//        payemntService.paymentNotification();
        System.out.println("Baking Process starts ");
        cakeBaker.bakeCake();

    }

}
