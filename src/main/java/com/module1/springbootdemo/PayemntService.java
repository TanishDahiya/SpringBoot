package com.module1.springbootdemo;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class PayemntService implements BeanNameAware {

    public void paymentNotification() {
        System.out.println("Payment Notification Service");
    }

    @Override
    public void setBeanName(String name) {

    }
}
