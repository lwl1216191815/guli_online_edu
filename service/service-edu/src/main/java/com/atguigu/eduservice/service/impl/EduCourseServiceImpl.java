package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.query.CourseQuery;
import com.atguigu.eduservice.entity.vo.CourseListVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 课程信息表 服务实现类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduChapterService eduChapterService;
    @Override
    public String saveCourse(CourseVo vo) {
        EduCourse entity = new EduCourse();
        BeanUtils.copyProperties(vo,entity);
        int insert = baseMapper.insert(entity);
        if(insert <= 0){
            throw new GuliException(2000,"添加课程信息失败");
        }
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(vo.getDescription());
        description.setId(entity.getId());
        eduCourseDescriptionService.save(description);
        return entity.getId();
    }

    @Override
    public CourseVo selectCourseVoById(String courseId) {
        EduCourse course = baseMapper.selectById(courseId);
        CourseVo res = new CourseVo();
        BeanUtils.copyProperties(course,res);
        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        res.setDescription(description.getDescription());
        return res;
    }

    @Override
    public void updateCourseByVo(CourseVo vo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(vo,course);
        int i = baseMapper.updateById(course);
        if(i == 0){
            throw new GuliException(20001,"修改课程信息失败");
        }
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(vo,description);
        eduCourseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo getCoursePublishInfo(String courseId) {
        CoursePublishVo detail = baseMapper.getCourseDetailById(courseId);
        return detail;
    }

    @Override
    public IPage<CourseListVo> pageList(Page<CourseListVo> coursePage, CourseQuery query) {
        IPage<CourseListVo> list = baseMapper.pageList(coursePage, query);
        return list;
    }

    @Override
    public void deleteDetailById(String courseId) {
        //删除章节、课时、视屏
        eduChapterService.deleteChapterByCourseId(courseId);
        //再删除课程
        boolean b = removeById(courseId);
        boolean b1 = eduCourseDescriptionService.removeById(courseId);
        if(!b && !b1){
            throw new GuliException(20001,"删除课程失败");
        }
    }
}
