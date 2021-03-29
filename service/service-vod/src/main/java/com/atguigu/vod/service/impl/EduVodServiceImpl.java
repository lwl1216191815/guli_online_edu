package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.vod.service.EduVodService;
import com.atguigu.vod.utils.ConstantPropertiesUtils;
import com.atguigu.vod.utils.VodClientInitUtils;
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

    @Override
    public void removeVideo(String videoId) {
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        DefaultAcsClient client = VodClientInitUtils.initClient(ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET);
        try {
            DeleteVideoResponse response = client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new GuliException(20001,"删除视屏失败");
        }
    }
}
