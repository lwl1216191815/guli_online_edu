package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.atguigu.vod.service.EduVodService;
import com.atguigu.vod.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class EduVodServiceImpl implements EduVodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String title = fileName.substring(0,fileName.lastIndexOf("."));
        try {
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            return response.getVideoId();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
