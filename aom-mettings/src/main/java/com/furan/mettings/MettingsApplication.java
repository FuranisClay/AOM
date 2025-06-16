package com.furan.mettings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.furan.mettings", "com.furan.aomcommon"})
public class MettingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MettingsApplication.class, args);
    }

}
