package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduLesson;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频（章节中的每一小节） 服务类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
public interface EduLessonService extends IService<EduLesson> {

    /**
     * 根据课程ID删除小节和视屏
     * @param courseId
     */
    void deleteByCourseId(String courseId);

    /**
     * 根据ID删除小节和视屏
     * @param videoId
     */
    void deleteById(String videoId);
}
