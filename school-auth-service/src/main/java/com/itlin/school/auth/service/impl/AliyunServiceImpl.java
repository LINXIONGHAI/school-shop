package com.itlin.school.auth.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.itlin.common.emun.BizCodeEnum;
import com.itlin.common.excepetion.BizException;
import com.itlin.common.util.JsonData;
import com.itlin.school.auth.service.AliyunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class AliyunServiceImpl implements AliyunService {
    @Override
    public JsonData uploudFile(MultipartFile file) {
        OSS ossClient=null;
        try {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "test-ling";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String format = sf.format(date);
        String objectName = format;
            String originalFilename = file.getOriginalFilename();
            String exit = originalFilename.substring(originalFilename.lastIndexOf("."));
            log.info("exit={}",exit);
            String fileTempName = UUID.randomUUID().toString().replace("-", "");
            objectName=objectName+"/"+fileTempName+exit;
            // 创建OSSClient实例。
         ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
            ossClient.putObject(bucketName, objectName, file.getInputStream());
            return JsonData.buildSuccess("https://"+bucketName+".oss-cn-shenzhen.aliyuncs.com"+"/"+objectName);
        } catch (Exception oe) {
            oe.printStackTrace();
            throw  new  BizException(BizCodeEnum.FILE_ERRO);
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
