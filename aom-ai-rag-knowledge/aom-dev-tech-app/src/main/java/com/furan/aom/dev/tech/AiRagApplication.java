package com.furan.aom.dev.tech;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configurable
public class AiRagApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiRagApplication.class);
    }

}
