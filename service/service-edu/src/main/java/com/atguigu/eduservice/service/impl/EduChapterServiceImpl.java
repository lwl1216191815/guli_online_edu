package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.CourseTreeNode;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
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
    private EduVideoService eduVideoService;

    @Override
    public List<CourseTreeNode> getTreeByCourseId(String courseId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapper);
        List<EduVideo> videoList = eduVideoService.list(wrapper);
        List<CourseTreeNode> res = new ArrayList<>();
        for (EduChapter chapter : chapterList) {
            CourseTreeNode node = new CourseTreeNode();
            BeanUtils.copyProperties(chapter,node);
            Iterator<EduVideo> it = videoList.iterator();
            List<CourseTreeNode> children = new ArrayList<>();
            while (it.hasNext()){
                EduVideo next = it.next();
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
}
