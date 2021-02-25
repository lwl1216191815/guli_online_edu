package com.atguigu.eduservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.vo.SubjectTreeNode;
import com.atguigu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
@Api(tags = {"课程分类前端控制器"})
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 导入课程分类
     * @param file 文件
     * @return
     */
    @PostMapping("/importSubject")
    @ApiOperation("导入课程分类")
    public R importSubject(MultipartFile file){
        eduSubjectService.importSubject(file);
        return R.ok();
    }

    /**
     * 以树形结构获取所有的课程分类
     * @return
     */
    @GetMapping("/getAllSubject")
    @ApiOperation("获取课程分类树")
    public R getAllSubject(){
        List<SubjectTreeNode> list = eduSubjectService.getSubjectTree();
        return R.ok().data("list",list);
    }
}

