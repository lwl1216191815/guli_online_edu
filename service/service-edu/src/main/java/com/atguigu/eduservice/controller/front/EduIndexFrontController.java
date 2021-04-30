package com.atguigu.eduservice.controller.front;

import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台首页controller
 */
@RestController
@RequestMapping("/eduservice/indexFront")
@CrossOrigin
public class EduIndexFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("/index")
    @ApiOperation("首页热门课程和热门讲师")
    public R index(){
        List<EduTeacher> teacherList = eduTeacherService.getPopularTeacher(4);
        List<EduCourse> courseList = eduCourseService.getPopularCourse(8);
        return R.ok().data("teacherList",teacherList).data("courseList",courseList);
    }
}
