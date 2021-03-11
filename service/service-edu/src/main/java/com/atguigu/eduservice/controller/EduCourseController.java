package com.atguigu.eduservice.controller;


import com.atguigu.common.constant.biz.CourseConstant;
import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.query.CourseQuery;
import com.atguigu.eduservice.entity.vo.CourseListVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseVo;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 获取课程发布信息id
     * @param courseId 课程ID
     * @return
     */
    @GetMapping("/getCoursePublishInfo/{courseId}")
    @ApiOperation("获取课程发布信息")
    public R getCoursePublishInfo(@PathVariable String courseId){
        CoursePublishVo vo = eduCourseService.getCoursePublishInfo(courseId);
        return R.ok().data("coursePublishInfo",vo);
    }

    /**
     * 课程发布
     * @param courseId 课程ID
     * @return
     */
    @ApiOperation("课程发布")
    @PutMapping("/publish/{courseId}")
    public R publish(@PathVariable String courseId){
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus(CourseConstant.HAD_PUBLISHED);
        boolean b = eduCourseService.updateById(course);
        return b ? R.ok() : R.error();
    }

    /**
     * 自定义分页条件查询课程
     * @param limit 每页记录数
     * @param page 当前页码
     * @param query 查询对象
     * @return
     */
    @PostMapping("/getPageList/{limit}/{page}")
    @ApiOperation("根据条件，分页获取课程列表")
    public R getPageList(@PathVariable("limit") @ApiParam("每页记录数") Integer limit,
                         @PathVariable("page") @ApiParam("当前页码") Integer page,
                         @RequestBody(required = false)CourseQuery query){
        Page<CourseListVo> coursePage = new Page<>(page, limit);
        IPage<CourseListVo> res = eduCourseService.pageList(coursePage, query);
        return R.ok().data("total", res.getTotal()).data("rows", res.getRecords());
    }

    /**
     * 根据课程ID删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("{courseId}")
    @ApiOperation("根据ID删除课程")
    public R removeCourse(@PathVariable String courseId){
        eduCourseService.deleteDetailById(courseId);
        return R.ok();
    }
}

