package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.query.CourseQuery;
import com.atguigu.eduservice.entity.vo.CourseListVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 课程信息表 Mapper 接口
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getCourseDetailById(@Param("courseId") String courseId);

    /**
     * 自定义分页条件查询
     * @param page 分页对象
     * @param query 查询条件
     * @return
     */
    IPage<CourseListVo> pageList(IPage<CourseListVo> page,@Param("q") CourseQuery query);
}
