package com.example.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@MapperScan("com.example.app.infrastructure.room.mapper")
@EnableJpaAuditing
public class JankenApplication {

    public static void main(String[] args) {
        SpringApplication.run(JankenApplication.class, args);
    }
}