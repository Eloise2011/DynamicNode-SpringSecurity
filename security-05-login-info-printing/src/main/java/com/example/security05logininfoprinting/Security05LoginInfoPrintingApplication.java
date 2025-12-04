package com.example.security05logininfoprinting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.example.security05logininfoprinting.mapper"})
public class Security05LoginInfoPrintingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Security05LoginInfoPrintingApplication.class, args);
    }

}
