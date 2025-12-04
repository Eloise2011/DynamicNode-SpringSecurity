package com.example.security03dbcustomizedloginpage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.example.security03dbcustomizedloginpage.mapper"})
public class Security03DbCustomizedLoginPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(Security03DbCustomizedLoginPageApplication.class, args);
    }

}
