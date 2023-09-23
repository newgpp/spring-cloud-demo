package com.felix.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    /**
     * 账户名称
     */
    private String accessKey;
    /**
     * 账户密码
     */
    private String secretKey;
    /**
     * MinIO连接地址，例：http://192.168.253.133:9000
     */
    private String endpoint;
    /**
     * 桶名称
     */
    private String bucket;
    /**
     * 访问文件的地址，例：http://192.168.253.133:9000
     */
    private String readPath;


    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getReadPath() {
        return readPath;
    }

    public void setReadPath(String readPath) {
        this.readPath = readPath;
    }
}
