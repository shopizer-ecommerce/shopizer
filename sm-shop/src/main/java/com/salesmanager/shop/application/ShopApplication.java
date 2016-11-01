package com.salesmanager.shop.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.salesmanager.shop.*")
public class ShopApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
