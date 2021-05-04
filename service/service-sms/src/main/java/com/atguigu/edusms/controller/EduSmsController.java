package com.atguigu.edusms.controller;

import com.atguigu.common.utils.R;
import com.atguigu.edusms.service.ISMSservice;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Api(tags = "龙帅在线教育，短信服务controller")
@RequestMapping("/edusms/sms")
public class EduSmsController {
    @Autowired
    private ISMSservice smsService;

    @PostMapping("/sendSms")
    public R send(String phone){
        if(StringUtils.isNotBlank(phone)){
            smsService.send(phone);
            return R.ok();
        }
        return R.error().message("发送验证码失败");
    }
}
