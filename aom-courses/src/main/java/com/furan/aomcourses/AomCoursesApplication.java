package com.furan.aomcourses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.furan.aomcourses", "com.furan.aomcommon"})
public class AomCoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AomCoursesApplication.class, args);
    }

}
