package com.example.springdemo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;

@SpringBootApplication
@ComponentScan({"com.example.springdemo1.*"})
public class Springdemo1Application {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

        SpringApplication.run(Springdemo1Application.class, args);
//        SpringApplication app = new SpringApplication(Springdemo1Application.class);
//        app.setDefaultProperties(Collections
//                .singletonMap("server.port", "8083"));
//        app.run(args);
    }


}
