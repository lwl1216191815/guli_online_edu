package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频（章节中的每一小节） 服务实现类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    // TODO 还得删除视屏
    @Override
    public void deleteByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        boolean result = remove(wrapper);
        if(!result){
            throw new GuliException(20001,"删除课时失败");
        }
    }
}
