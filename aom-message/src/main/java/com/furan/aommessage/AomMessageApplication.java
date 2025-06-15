package com.furan.aommessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.furan.aommessage", "com.furan.aomcommon"})
public class AomMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AomMessageApplication.class, args);
	}

}
