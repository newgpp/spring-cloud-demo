package com.felix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.service.PreSignedUrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/storage")
public class PreSignedStorageController {

    @Resource
    private PreSignedUrlService preSignedUrlService;

    @GetMapping("/url/upload/get")
    public Object getPreSignedUploadUrl(@RequestParam("fileName") String fileName){

        String preSignedUploadUrl = preSignedUrlService.getPreSignedUploadUrl(fileName);

        return new ObjectMapper().createObjectNode().put("url", preSignedUploadUrl);
    }

    @GetMapping("/url/download/get")
    public Object getPreSignedDownloadUrl(@RequestParam("fileName") String fileName){

        String preSignedDownloadUrl = preSignedUrlService.getPreSignedDownloadUrl(fileName);

        return new ObjectMapper().createObjectNode().put("url", preSignedDownloadUrl);
    }

}
