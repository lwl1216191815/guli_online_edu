package com.atguigu.oss.controller;

import com.atguigu.common.utils.R;
import com.atguigu.oss.service.IOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduOss/fileOss")
@Api("文件上传，阿里云oss控制器")
@CrossOrigin
public class OssController {
    @Autowired
    private IOssService ossService;

    @PostMapping
    @ApiOperation("上传用户头像，然后返回头像的url")
    public R uploadAvatar(MultipartFile file){
        String url = ossService.uploadAvatar(file);
        return R.ok().data("url",url);
    }
}
