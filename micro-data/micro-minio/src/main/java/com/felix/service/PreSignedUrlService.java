package com.felix.service;

import com.felix.config.MinioProperties;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class PreSignedUrlService {

    private static final Logger log = LoggerFactory.getLogger(PreSignedUrlService.class);

    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioProperties minioProperties;

    public String getPreSignedUploadUrl(String objectName) {
        GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT)
                .bucket(minioProperties.getBucket())
                .object(objectName)
                .expiry(1, TimeUnit.DAYS)
                .build();
        String url = null;
        try {
            url = minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);
        } catch (Exception e) {
            log.error("PreSignedUrl error: ", e);
        }
        log.info("====> PreSignedUrl upload {}", url);
        return url;
    }

    public String getPreSignedDownloadUrl(String objectName) {
        GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(minioProperties.getBucket())
                .object(objectName)
                .expiry(1, TimeUnit.DAYS)
                .build();
        String url = null;
        try {
            url = minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);
        } catch (Exception e) {
            log.error("PreSignedUrl error: ", e);
        }
        log.info("====> PreSignedUrl download {}", url);
        return url;
    }

}
