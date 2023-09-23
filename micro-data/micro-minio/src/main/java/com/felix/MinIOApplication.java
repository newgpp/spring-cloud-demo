package com.felix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author felix
 * @desc some desc
 */
@SpringBootApplication
@EnableEurekaClient
public class MinIOApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinIOApplication.class, args);
    }
}
