package com.tongji.ems.grade;

import com.tongji.ems.feign.clients.CourseClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {CourseClient.class})
public class GradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(GradeApplication.class, args);
    }
}
