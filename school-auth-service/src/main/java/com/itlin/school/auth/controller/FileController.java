package com.itlin.school.auth.controller;

import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.util.JsonData;
import com.itlin.school.auth.service.AliyunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/file/v1")
@Slf4j
public class FileController {

    @Resource
    private AliyunService aliyunService;

    @PostMapping("/uploud")
    public JsonData uploud(@RequestParam("file") MultipartFile file) {
        try {
            return aliyunService.uploudFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            BizException bizException= (BizException) e;
            throw bizException;
        }

    }


}
