package com.atguigu.eduservice.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Teacher查询对象
 */
@Data
public class TeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;
    /**
     * 讲师级别
     */
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
