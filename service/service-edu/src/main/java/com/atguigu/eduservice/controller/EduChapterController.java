package com.atguigu.eduservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.vo.CourseTreeNode;
import com.atguigu.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程章节表 前端控制器
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
@Api(tags = "课程章节前端控制器")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 根据课程ID获取下面的章节和小节，以树形结构展示
     * @param courseId
     * @return
     */
    @GetMapping("/getChapterVideo/{courseId}")
    @ApiOperation("根据课程ID获取章节、小节树")
    public R getChapterVideo(@PathVariable @ApiParam("课程ID") String courseId){
        if(StringUtils.isNotBlank(courseId)){
            List<CourseTreeNode> nodeList = eduChapterService.getTreeByCourseId(courseId);
            return R.ok().data("list",nodeList);
        }
        return R.error().code(20002).message("课程ID不能为空");
    }
}

