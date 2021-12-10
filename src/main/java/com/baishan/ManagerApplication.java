package com.baishan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ManagerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ManagerApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(ManagerApplication.class, args);
        System.out.println("| ---------------------------------------------------------------------------------- |");
        System.out.println("|                                    Started Success                                 |");
        System.out.println("| ---------------------------------------------------------------------------------- |");
    }
}
