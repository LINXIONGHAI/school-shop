package com.itlin.school.auth;

import com.itlin.common.util.JsonData;
import com.itlin.school.auth.controller.AddressController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
@Slf4j
public class AuthApplicationTest {

    @Resource
    private AddressController addressController;

    @Test
    public void  addressTest(){
        JsonData jsonData = addressController.get(1L);
        log.info("jsonData={}",jsonData);
    }
}
