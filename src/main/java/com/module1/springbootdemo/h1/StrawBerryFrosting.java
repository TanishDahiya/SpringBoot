package com.module1.springbootdemo.h1;

import org.springframework.stereotype.Component;

@Component
public class StrawBerryFrosting implements Frosting{
    @Override
    public String getFrostingType() {
        System.out.println("StrawBerry Frosting");
        return "Strawberry Frosting";
    }
}
