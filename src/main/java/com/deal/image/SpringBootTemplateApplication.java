package com.deal.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTemplateApplication.class, args);
    }

}
