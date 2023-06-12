package com.smarthome.shuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShUserServiceApplication.class, args);
    }

}
