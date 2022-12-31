package com.tongji.ems.experimentmanage;

import com.tongji.ems.feign.clients.PersonalInfoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@SpringBootApplication
@EnableFeignClients(clients = {PersonalInfoClient.class})
public class ExperimentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExperimentApplication.class, args);
    }
}