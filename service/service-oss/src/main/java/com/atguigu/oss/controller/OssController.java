package com.atguigu.oss.controller;

import com.atguigu.common.utils.R;
import com.atguigu.oss.service.IOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduOss/fileOss")
public class OssController {
    @Autowired
    private IOssService ossService;

    @PostMapping
    public R uploadAvatar(MultipartFile file){
        String url = ossService.uploadAvatar(file);
        return R.ok().data("url",url);
    }
}
