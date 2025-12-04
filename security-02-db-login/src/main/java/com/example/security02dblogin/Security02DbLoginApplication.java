package com.example.security02dblogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.example.security02dblogin.mapper"})
public class Security02DbLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(Security02DbLoginApplication.class, args);
    }

}
