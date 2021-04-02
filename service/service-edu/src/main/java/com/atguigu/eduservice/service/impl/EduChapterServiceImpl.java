package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduLesson;
import com.atguigu.eduservice.entity.tree.CourseTreeNode;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduLessonService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 课程章节表 服务实现类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduLessonService eduLessonService;

    @Override
    public List<CourseTreeNode> getTreeByCourseId(String courseId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        wrapper.orderByAsc("sort");
        List<EduChapter> chapterList = baseMapper.selectList(wrapper);
        List<EduLesson> lessonList = eduLessonService.list(wrapper);
        List<CourseTreeNode> res = new ArrayList<>();
        for (EduChapter chapter : chapterList) {
            CourseTreeNode node = new CourseTreeNode();
            BeanUtils.copyProperties(chapter,node);
            Iterator<EduLesson> it = lessonList.iterator();
            List<CourseTreeNode> children = new ArrayList<>();
            while (it.hasNext()){
                EduLesson next = it.next();
                if(next.getChapterId().equals(chapter.getId())){
                    CourseTreeNode childNode = new CourseTreeNode();
                    BeanUtils.copyProperties(next,childNode);
                    children.add(childNode);
                    it.remove();
                }
            }
            node.setChildren(children);
            res.add(node);
        }
        return res;
    }

    @Override
    public boolean deleteChapterById(String chapterId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chapter_id",chapterId);
        int count = eduLessonService.count(wrapper);
        if(count > 0){
            throw new GuliException(20001,"本章节下还有小节，无法删除");
        }
        int i = baseMapper.deleteById(chapterId);
        return i > 0;
    }

    @Override
    public void deleteChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        eduLessonService.deleteByCourseId(courseId);
        boolean b = remove(wrapper);
        if (!b){
            throw new GuliException(20001,"删除章节失败");
        }
    }
}
