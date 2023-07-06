package com.smarthome.shuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableJpaRepositories("com.smarthome.shuserservice.repo")
@EntityScan(basePackageClasses = {
        ShUserServiceApplication.class,
        Jsr310JpaConverters.class
})
public class ShUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShUserServiceApplication.class, args);
    }

}
