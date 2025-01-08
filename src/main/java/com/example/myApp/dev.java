package com.example.myApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class dev {
    @Autowired
    private Laptop laptop;
    public void build(){
        laptop.compile();
        System.out.println("Developer builds awesome stuff");
    }
}
