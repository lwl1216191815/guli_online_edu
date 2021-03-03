package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程信息表 服务类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 保存课程信息和课程简介
     * @param vo
     */
    String saveCourse(CourseVo vo);

    /**
     *根据课程ID获取课程信息
     * @param courseId
     * @return
     */
    CourseVo selectCourseVoById(String courseId);

    /**
     * 修改课程信息
     * @param vo
     */
    void updateCourseByVo(CourseVo vo);
}
