package com.atguigu.edusms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.atguigu"})
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class);
        System.out.println("在线教育验证码系统启动成功");
    }
}
