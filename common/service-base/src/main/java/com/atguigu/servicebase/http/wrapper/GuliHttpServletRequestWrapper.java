package com.atguigu.servicebase.http.wrapper;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * 自定义HttpServletRequestWrapper，用于处理请求参数
 */
public class GuliHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] body;
    public GuliHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        byte[] cache = StreamUtils.copyToByteArray(request.getInputStream());
        if(cache != null && cache.length > 0){
            String body = new String(cache,request.getCharacterEncoding());
            Map<String,Object> map = JSONUtil.toBean(body, Map.class);
            Iterator<String> keySet = map.keySet().iterator();
            while (keySet.hasNext()){
                String key = keySet.next();
                if(map.get(key) != null){
                    String valueStr = map.get(key).toString().trim();
                    valueStr = "null".equals(valueStr) ? null : valueStr;
                    map.put(key,StrUtil.emptyToNull(valueStr));
                }
            }
            this.body = JSONUtil.toJsonStr(map).getBytes();
        }else {
            this.body = cache;
        }

    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        InputStream bodyStream = new ByteArrayInputStream(body);
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bodyStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        InputStream bodyStream = new ByteArrayInputStream(body);
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
