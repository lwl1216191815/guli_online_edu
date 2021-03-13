package com.atguigu.vod.controller;

import com.atguigu.common.utils.R;
import com.atguigu.vod.service.EduVodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduVod/vod")
@Api(tags = "视屏上传，阿里云控制controller")
public class EduVodController {
    @Autowired
    private EduVodService eduVodService;

    @PostMapping("/uploadVideo")
    @ApiOperation("课时上传视屏")
    public R uploadVideo(MultipartFile file){
        String videoId = eduVodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }
}
