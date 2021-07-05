package com.cycas.commonutil;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.cycas.commonutil.dao")
public class CommonUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonUtilApplication.class, args);
    }

}
