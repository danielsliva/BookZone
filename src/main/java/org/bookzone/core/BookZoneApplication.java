package org.bookzone.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookZoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookZoneApplication.class, args);
    }

    @Bean
    public CheckUser checkUser(){
        return new CheckUser();
    }

}
