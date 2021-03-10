package com.atguigu.eduservice.config;

import com.atguigu.servicebase.http.servlet.GuliDispatherServlet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web相关配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 使用自定义的dispatcherServlet
     * @return
     */
    @Bean
    @Qualifier(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet(){
        return new GuliDispatherServlet();
    }
}
