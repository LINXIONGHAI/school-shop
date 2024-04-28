package com.itlin.school.auth.service;

import com.itlin.common.util.JsonData;
import org.springframework.web.multipart.MultipartFile;

public interface AliyunService {
    JsonData uploudFile(MultipartFile file);
}
