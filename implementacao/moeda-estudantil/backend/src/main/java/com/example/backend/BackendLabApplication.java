package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BackendLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendLabApplication.class, args);
    }

}
