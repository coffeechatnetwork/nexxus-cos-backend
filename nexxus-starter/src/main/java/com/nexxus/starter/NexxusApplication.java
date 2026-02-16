package com.nexxus.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nexxus")
@MapperScan("com.nexxus.**.mapper")
public class NexxusApplication {

    public static void main(String[] args) {
        SpringApplication.run(NexxusApplication.class, args);
    }

}
