package com.felix;

import io.minio.*;
import io.minio.http.Method;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MinioTest {

    private static final String MINIO_SERVER_ADDRESS = "http://192.168.197.101:9000";
    private static final String ACCESS_KEY = "minio";
    private static final String SECRET_KEY = "minio123";
    private static final String BUCKET_NAME = "bucket-felix";

    private MinioClient minioClient;

    @Before
    public void init() {
        minioClient = MinioClient.builder()
                .endpoint(MINIO_SERVER_ADDRESS)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
    }

    @Test
    @Ignore
    public void upload_should_success() throws Exception {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("butterfly.jpg")) {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object("butterfly.jpg")
                    .stream(inputStream, inputStream.available(), -1)
                    .contentType("image/jpg")
                    .build();
            minioClient.putObject(putObjectArgs);
        }
    }

    @Test
    @Ignore
    public void download_should_success() throws Exception {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .object("butterfly.jpg")
                .build();
        String uuid = UUID.randomUUID().toString();
        try (GetObjectResponse response = minioClient.getObject(getObjectArgs);
             FileOutputStream outputStream = new FileOutputStream(String.format("d:/%s.jpg", uuid))) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = response.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
        }
    }

    @Test
    @Ignore
    public void remove_should_success() throws Exception {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .object("butterfly.jpg")
                .build();
        minioClient.removeObject(removeObjectArgs);
    }

    @Test
    @Ignore
    public void get_pre_sign_upload_url() throws Exception {
        String objectName = "insect.jpg";
        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.PUT)
                        .bucket(BUCKET_NAME)
                        .object(objectName)
                        .expiry(1, TimeUnit.DAYS)
                        .build());
        System.out.println(url);
    }

    @Test
    @Ignore
    public void get_pre_sign_download_url() throws Exception {
        String objectName = "insect.jpg";
        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(BUCKET_NAME)
                        .object(objectName)
                        .expiry(1, TimeUnit.DAYS)
                        .build());
        System.out.println(url);
    }
}
