package com.felix.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 初始MinIO连接对象配置类
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinIOConfiguration {
    /**
     * 注入属性配置类
     */
    @Resource
    private MinioProperties minioProperties;

    /**
     * 将Minio的客户端对象，加入IOC容器
     */
    @Bean
    public MinioClient createMinioClient() {
        return MinioClient.builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(minioProperties.getEndpoint())
                .build();
    }
}