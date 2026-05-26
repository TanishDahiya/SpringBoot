package com.module1.springbootdemo.h1;

import org.springframework.stereotype.Component;

@Component
public class StrawBerrySyrup implements Syrup{
    @Override
    public String getSyrupType() {
        System.out.println("StrawBerry Syrup");
        return "StrawBerry Syrup";
    }
}
