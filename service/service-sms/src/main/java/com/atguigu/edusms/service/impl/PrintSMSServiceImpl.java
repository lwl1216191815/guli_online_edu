package com.atguigu.edusms.service.impl;

import com.atguigu.edusms.service.ISMSservice;
import com.atguigu.edusms.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PrintSMSServiceImpl implements ISMSservice {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public Boolean send(String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if(StringUtils.isBlank(code)){
            code = RandomUtil.getFourBitRandom();
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
        }
        System.out.println("验证码为："+code);
        return true;
    }
}
