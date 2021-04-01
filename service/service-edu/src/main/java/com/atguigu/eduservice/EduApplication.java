package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
@EnableDiscoveryClient//表示可以通过nacos提供服务
@EnableFeignClients//表示可以通过feign调用服务
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
        System.out.println("谷粒学院-在线教育项目启动成功");
    }
}
