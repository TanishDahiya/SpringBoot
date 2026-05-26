package com.module1.springbootdemo.h1;

import org.springframework.stereotype.Component;

@Component
public class ChocolateSyrup implements Syrup {
    @Override
    public String getSyrupType() {
        System.out.println("Chocolate Syrup");
        return "Chocolate Syrup";
    }
}
