package com.module1.springbootdemo.h1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    private Frosting frosting;
    private Syrup syrup;
    public CakeBaker(@Qualifier("chocolateFrosting") Frosting frosting,@Qualifier("chocolateSyrup") Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("Baking Cake");
        frosting.getFrostingType();
        syrup.getSyrupType();
    }
}
