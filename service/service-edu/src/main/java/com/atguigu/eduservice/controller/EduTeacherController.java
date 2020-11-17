package com.atguigu.eduservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author liuwenlong
 * @since 2020-11-13
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 获取所有讲师列表
     * @return
     */
    @GetMapping()
    @ApiOperation("所有讲师列表")
    public R list(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    /**
     * 删除讲师（逻辑删除）
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("根据ID删除讲师")
    public R removeBtId(@ApiParam(name = "id",value = "讲师ID",required = true)
                                  @PathVariable String id){
        eduTeacherService.removeById(id);
        return R.ok();
    }
}

