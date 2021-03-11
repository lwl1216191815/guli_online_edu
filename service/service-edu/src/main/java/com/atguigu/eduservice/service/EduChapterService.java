package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.tree.CourseTreeNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程章节表 服务类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 根据课程ID获取课程树
     * @param courseId
     * @return
     */
    List<CourseTreeNode> getTreeByCourseId(String courseId);

    /**
     * 根据章节ID删除章节
     * @param chapterId
     * @return
     */
    boolean deleteChapterById(String chapterId);

    /**
     * 根据课程ID删除章节和章节下的小节
     * @param courseId
     */
    void deleteChapterByCourseId(String courseId);
}
