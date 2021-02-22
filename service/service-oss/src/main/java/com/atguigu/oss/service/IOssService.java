package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface IOssService {
    /**
     * 上传头像的方法
     * @param file
     * @return
     */
    String uploadAvatar(MultipartFile file);
}
