package com.example.vegetableecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories(value = "com.example.vegetableecommerce.repository")
public class VegetableecommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VegetableecommerceApplication.class, args);
    }

}
