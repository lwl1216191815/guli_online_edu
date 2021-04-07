package com.atguigu.eduservice.client;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name="service-vod",fallback = VodClientImpl.class)
public interface VodClient {
    /**
     * 根据视屏ID删除阿里云上的视屏
     * @param videoId
     * @return
     */
    @DeleteMapping("/eduVod/vod/{videoId}")
    R deleteVideo(@PathVariable("videoId") String videoId);
}
