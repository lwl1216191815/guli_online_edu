package com.atguigu.eduservice.entity.vo;

import lombok.Data;

@Data
public class CoursePublishVo {

    private String id;

    private String courseName;

    private String teacherName;

    private String cover;

    private Double price;

    private String subjectName;

    private String subjectParentName;

    private String description;

    private Integer lessonNum;
}
