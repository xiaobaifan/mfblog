package com.mofan.mfbolg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("com.mofan.mfbolg.mapper")
//@CrossOrigin(origins = "http://localhost:3100", allowedHeaders = "*")
public class MfbolgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MfbolgApplication.class, args);
    }

}
