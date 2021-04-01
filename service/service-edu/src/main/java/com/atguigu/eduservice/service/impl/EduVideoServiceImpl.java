package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
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
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private VodClient vodClient;

    @Override
    public void deleteByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        List<EduVideo> list = list(wrapper);
        for (EduVideo video : list) {
            deleteAll(video);
        }
    }

    @Override
    public void deleteById(String videoId) {
        EduVideo video = getById(videoId);
        if(video != null && StringUtils.isNotBlank(video.getVideoSourceId())){
            deleteAll(video);
        }
    }

    /**
     * 删除课时以及视屏
     * @param video
     */
    private void deleteAll(EduVideo video){
        vodClient.deleteVideo(video.getVideoSourceId());
        removeById(video.getId());
    }
}
