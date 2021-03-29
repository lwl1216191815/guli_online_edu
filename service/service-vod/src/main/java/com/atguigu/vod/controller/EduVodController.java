package com.atguigu.vod.controller;

import com.atguigu.common.utils.R;
import com.atguigu.vod.service.EduVodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduVod/vod")
@Api(tags = "视屏上传，阿里云控制controller")
@CrossOrigin
public class EduVodController {
    @Autowired
    private EduVodService eduVodService;

    @PostMapping("/uploadVideo")
    @ApiOperation("课时上传视屏")
    public R uploadVideo(MultipartFile file){
        String videoId = eduVodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    @DeleteMapping("{videoId}")
    @ApiOperation("删除视屏")
    public R deleteVideo(@PathVariable("videoId") @ApiParam(name = "videoId",value = "云端视屏ID",required = true) String videoId){
        eduVodService.removeVideo(videoId);
        return R.ok().message("删除视屏成功");
    }
}
