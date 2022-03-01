package com.ai13qcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Ai13qcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ai13qcmApplication.class, args);
    }

}
