package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.query.CourseQuery;
import com.atguigu.eduservice.entity.vo.CourseListVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /**
     * 根据课程ID获取课程发布信息
     * @param courseId
     * @return
     */
    CoursePublishVo getCoursePublishInfo(String courseId);

    /**
     * 自定义分页条件查询课程列表
     * @param coursePage 分页对象
     * @param query 查询条件
     * @return
     */
    IPage<CourseListVo> pageList(Page<CourseListVo> coursePage, CourseQuery query);

    /**
     * 根据课程ID删除课程、课程详情、章节、小节
     * @param courseId
     */
    void deleteDetailById(String courseId);

    /**
     * 获取排名前limit的热门课程
     * @param limit
     * @return
     */
    List<EduCourse> getPopularCourse(Integer limit);
}
