package com.felix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.service.MinIOFileStorageService;
import com.felix.service.PreSignedUrlService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private PreSignedUrlService preSignedUrlService;
    @Resource
    private MinIOFileStorageService minIOFileStorageService;

    @GetMapping("/url/upload/get")
    public Object getPreSignedUploadUrl(@RequestParam("fileName") String fileName) {

        String preSignedUploadUrl = preSignedUrlService.getPreSignedUploadUrl(fileName);

        return new ObjectMapper().createObjectNode().put("url", preSignedUploadUrl);
    }

    @GetMapping("/url/download/get")
    public Object getPreSignedDownloadUrl(@RequestParam("fileName") String fileName) {

        String preSignedDownloadUrl = preSignedUrlService.getPreSignedDownloadUrl(fileName);

        return new ObjectMapper().createObjectNode().put("url", preSignedDownloadUrl);
    }

    @PostMapping("/upload")
    public Object upload(@RequestParam("fileName") String fileName, @RequestPart("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String url = minIOFileStorageService.uploadImgFile("", fileName, inputStream);
            return new ObjectMapper().createObjectNode().put("url", url);
        } catch (IOException e) {
            return new ObjectMapper().createObjectNode().put("error", e.getMessage());
        }
    }

}
