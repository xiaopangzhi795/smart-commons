package com.geek45.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.geek45")
public class SmartCommonsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCommonsTestApplication.class, args);
    }

}
