package com.example.security04captchalogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.example.security04captchalogin.mapper"})
public class Security04CaptchaLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(Security04CaptchaLoginApplication.class, args);
    }

}
