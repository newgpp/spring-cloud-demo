package com.felix;

import com.felix.config.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author felix
 * @desc some desc
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties(MinioProperties.class)
public class MinIOApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinIOApplication.class, args);
    }
}
