package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 阿里云视屏点播
 */
public interface EduVodService {
    /**
     * 向阿里云中上传视屏，返回视屏ID
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 删除阿里云上的视屏
     * @param videoId
     */
    void removeVideo(String videoId);
}
