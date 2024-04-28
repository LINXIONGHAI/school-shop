package com.itlin.school.auth.controller;

import com.itlin.common.util.JsonData;
import com.itlin.school.auth.service.AliyunService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/file/v1")
public class FileController {

    @Resource
    private AliyunService aliyunService;

    @PostMapping("/uploud")
    public JsonData uploud(@RequestParam("file") MultipartFile file){
       return aliyunService.uploudFile(file);

    }


}
