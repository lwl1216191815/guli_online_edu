package com.atguigu.edusms.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.edusms.service.ISMSservice;
import com.atguigu.edusms.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.aliyuncs.exceptions.ServerException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SMSServiceImpl implements ISMSservice {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Boolean send(String phone) {
        //先从redis中取，如果有就直接返回，可以防止重复发送
        String code = redisTemplate.opsForValue().get(phone);
        if(StringUtils.isNotBlank(code)){
            return true;
        }
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putBodyParameter("PhoneNumbers",phone);
        Map<String,Object> param = new HashMap<>();
        code = RandomUtil.getFourBitRandom();
        param.put("code", code);
        request.putBodyParameter("TemplateCode", JSONUtil.toJsonStr(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            if(success){
               redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            }
            return success;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
