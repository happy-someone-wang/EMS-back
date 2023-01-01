package com.tongji.ems.personalinfo;

import com.tongji.ems.feign.clients.FileStoreClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.tongji.ems.personalinfo.mapper")
@EnableFeignClients(clients = {FileStoreClient.class})
public class PersonalInfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonalInfoApplication.class, args);
    }
}