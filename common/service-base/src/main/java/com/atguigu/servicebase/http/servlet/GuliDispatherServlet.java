package com.atguigu.servicebase.http.servlet;

import com.atguigu.servicebase.http.wrapper.GuliHttpServletRequestWrapper;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义 DispatcherServlet 来分派 XinHttpServletRequestWrapper
 */
public class GuliDispatherServlet extends DispatcherServlet {
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.doDispatch(new GuliHttpServletRequestWrapper(request), response);
    }
}
