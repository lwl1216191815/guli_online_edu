package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.tree.SubjectTreeNode;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-23
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 导入课程分类
     * @param file
     */
    void importSubject(MultipartFile file);

    /**
     * 获取课程分类树
     * @return
     */
    List<SubjectTreeNode> getSubjectTree();
}
