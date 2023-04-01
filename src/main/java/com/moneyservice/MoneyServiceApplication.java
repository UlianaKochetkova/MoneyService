package com.moneyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MoneyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyServiceApplication.class, args);
    }

}
