package com.module1.springbootdemo.h1;

import org.springframework.stereotype.Component;

@Component
public class ChocolateFrosting implements Frosting {
    @Override
    public String getFrostingType() {
        System.out.println("Chocolate Frosting");
        return "Chocolate Frosting";
    }
}
