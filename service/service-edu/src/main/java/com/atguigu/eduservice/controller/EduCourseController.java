package com.atguigu.eduservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.vo.CourseVo;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程信息表 前端控制器
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
@Api(tags = {"课程前端控制器"})
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("/addCourse")
    @ApiOperation("添加课程")
    public R addCourse(@RequestBody CourseVo vo){
        eduCourseService.saveCourse(vo);
        return R.ok();
    }
}

