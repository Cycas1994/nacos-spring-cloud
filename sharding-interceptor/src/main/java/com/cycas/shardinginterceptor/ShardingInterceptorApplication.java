package com.cycas.shardinginterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cycas.shardinginterceptor.dao")
public class ShardingInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingInterceptorApplication.class, args);
    }

}
