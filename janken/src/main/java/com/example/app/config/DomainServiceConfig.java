package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.app.domain.janken.service.JankenGameEngine;
import com.example.app.domain.janken.service.JankenResultService;

@Configuration
public class DomainServiceConfig {

    @Bean
    public JankenGameEngine jankenGameEngine() {
        return new JankenGameEngine();
    }

    @Bean
    public JankenResultService jankenResultService() {
        return new JankenResultService();
    }
}

