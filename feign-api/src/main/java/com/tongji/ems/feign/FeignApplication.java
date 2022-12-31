package com.tongji.ems.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeignApplication {
    public static final String a = "1234";

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}
