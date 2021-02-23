package com.atguigu.eduservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 导入课程分类
     * @param file 文件
     * @return
     */
    @PostMapping("/importSubject")
    public R importSubject(MultipartFile file){
        eduSubjectService.importSubject(file);
        return R.ok();
    }
}

