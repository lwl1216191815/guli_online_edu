package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduLesson;
import com.atguigu.eduservice.mapper.EduLessonMapper;
import com.atguigu.eduservice.service.EduLessonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频（章节中的每一小节） 服务实现类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
@Service
public class EduLessonServiceImpl extends ServiceImpl<EduLessonMapper, EduLesson> implements EduLessonService {
    @Autowired
    private VodClient vodClient;

    @Override
    public void deleteByCourseId(String courseId) {
        QueryWrapper<EduLesson> wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        List<EduLesson> list = list(wrapper);
        for (EduLesson lesson : list) {
            deleteAll(lesson);
        }
    }

    @Override
    public void deleteById(String videoId) {
        EduLesson lesson = getById(videoId);
        if(lesson != null && StringUtils.isNotBlank(lesson.getVideoSourceId())){
            deleteAll(lesson);
        }
    }

    /**
     * 删除课时以及视屏
     * @param lesson
     */
    private void deleteAll(EduLesson lesson){
        vodClient.deleteVideo(lesson.getVideoSourceId());
        removeById(lesson.getId());
    }
}
