package com.ciazhar.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaRepositories
@EnableAsync
@SpringBootApplication
public class PostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresApplication.class, args);
    }

}
