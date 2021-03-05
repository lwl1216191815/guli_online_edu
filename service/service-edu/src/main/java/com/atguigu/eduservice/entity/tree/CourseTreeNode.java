package com.atguigu.eduservice.entity.tree;

import lombok.Data;

import java.util.List;

/**
 * 课程-章节-小节树节点
 */
@Data
public class CourseTreeNode {

    private String id;

    private String title;

    private List<CourseTreeNode> children;
}
