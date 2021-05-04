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
import org.springframework.stereotype.Service;
import com.aliyuncs.exceptions.ServerException;

import java.util.HashMap;
import java.util.Map;

@Service
public class SMSServiceImpl implements ISMSservice {
    @Override
    public Boolean send(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putBodyParameter("PhoneNumbers",phone);
        Map<String,Object> param = new HashMap<>();
        param.put("code", RandomUtil.getFourBitRandom());
        request.putBodyParameter("TemplateCode", JSONUtil.toJsonStr(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
