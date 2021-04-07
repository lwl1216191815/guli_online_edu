package com.atguigu.eduservice.client.impl;

import com.atguigu.common.utils.R;
import com.atguigu.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

@Component
public class VodClientImpl implements VodClient {
    @Override
    public R deleteVideo(String videoId) {
        return R.error().message("删除视屏失败");
    }
}
