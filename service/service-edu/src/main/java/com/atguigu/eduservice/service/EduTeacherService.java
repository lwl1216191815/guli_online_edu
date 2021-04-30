package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author liuwenlong
 * @since 2020-11-13
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     * 获取排名前limit的热门老师
     * @param limit
     * @return
     */
    List<EduTeacher> getPopularTeacher(Integer limit);
}
