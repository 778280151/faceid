package com.safecode.faceid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:static/application.properties")
public class FaceidApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceidApplication.class, args);
    }

}