package org.its.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.its")
@SpringBootApplication
public class SpringBootWebApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApp.class, args);
    }
}

