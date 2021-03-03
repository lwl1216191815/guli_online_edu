package com.atguigu.eduservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.vo.CourseVo;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    /**
     * 添加课程
     * @param vo 参数对象
     * @return
     */
    @PostMapping("/addCourse")
    @ApiOperation("添加课程")
    public R addCourse(@RequestBody CourseVo vo){
        String courseId = eduCourseService.saveCourse(vo);
        return R.ok().data("courseId",courseId);
    }

    /**
     * 根据课程ID获取课程信息
     * @param courseId
     * @return
     */
    @GetMapping("/getCourseById/{courseId}")
    @ApiOperation("根据课程ID获取课程信息")
    public R getCourseById(@PathVariable @ApiParam("课程id") String courseId){
        CourseVo vo = eduCourseService.selectCourseVoById(courseId);
        return R.ok().data("data",vo);
    }

    /**
     * 修改课程信息
     * @param vo
     * @return
     */
    @PutMapping("/updateCourse")
    @ApiOperation("修改课程信息")
    public R updateCourse(@RequestBody CourseVo vo){
        eduCourseService.updateCourseByVo(vo);
        return R.ok();
    }
}

