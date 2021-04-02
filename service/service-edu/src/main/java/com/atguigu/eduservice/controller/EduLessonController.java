package com.atguigu.eduservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.EduLesson;
import com.atguigu.eduservice.service.EduLessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频（章节中的每一小节） 前端控制器
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/eduservice/edu-lesson")
@CrossOrigin
@Api(tags = "课时前端控制器")
public class EduLessonController {
    @Autowired
    private EduLessonService eduLessonService;

    /**
     * 添加课程小节
     * @param eduLesson
     * @return
     */
    @ApiOperation("添加课时（课程小节）")
    @PostMapping("/addLesson")
    public R addLesson(@RequestBody EduLesson eduLesson){
        eduLessonService.save(eduLesson);
        return R.ok();
    }

    /**
     * 修改课程小节
     * @param lesson
     * @return
     */
    @PutMapping("/editLesson")
    @ApiOperation("修改课程小节（课时）")
    public R modifyLesson(@RequestBody EduLesson lesson){
        eduLessonService.updateById(lesson);
        return R.ok();
    }

    /**
     * 根据ID获取课时详情
     * @param lessonId
     * @return
     */
    @GetMapping("{lessonId}")
    @ApiOperation("根据ID获取课时详情")
    public R getLessonById(@PathVariable String lessonId){
        EduLesson lesson = eduLessonService.getById(lessonId);
        return R.ok().data("lesson",lesson);
    }

    /**
     * 根据ID删除课程小节
     * @param lessonId
     * @return
     */
    @DeleteMapping("{lessonId}")
    @ApiOperation("根据ID删除课时")
    public R removeLessonById(@PathVariable String lessonId){
        eduLessonService.deleteById(lessonId);
        return R.ok();
    }
}

