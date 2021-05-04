package com.atguigu.edusms.service;

public interface ISMSservice {
    /**
     * 发送验证码
     * @param phone
     * @return
     */
    Boolean send(String phone);
}
