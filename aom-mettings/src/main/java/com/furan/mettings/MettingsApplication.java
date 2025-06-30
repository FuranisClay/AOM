package com.furan.mettings;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo(scanBasePackages = "com.furan.mettings.service.impl")
@SpringBootApplication(scanBasePackages = {"com.furan.mettings", "com.furan.aomcommon"})
public class MettingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MettingsApplication.class, args);
    }

}
