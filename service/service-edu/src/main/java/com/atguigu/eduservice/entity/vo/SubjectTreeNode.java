package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 课程分类树节点
 */
@Data
public class SubjectTreeNode {
    /**
     * id
     */
    private String id;
    /**
     * 节点名称
     */
    private String label;
    /**
     * 子节点
     */
    private List<SubjectTreeNode> children;
}
