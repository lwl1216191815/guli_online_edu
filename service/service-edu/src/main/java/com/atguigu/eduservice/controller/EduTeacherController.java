package com.atguigu.eduservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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
@Api(tags = {"讲师前端控制器"})
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 获取所有讲师列表
     *
     * @return
     */
    @GetMapping()
    @ApiOperation("所有讲师列表")
    public R list() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    /**
     * 删除讲师（逻辑删除）
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("根据ID删除讲师")
    public R removeBtId(@ApiParam(name = "id", value = "讲师ID", required = true)
                        @PathVariable String id) {
        Boolean flag = eduTeacherService.removeById(id);
        return flag ? R.ok() : R.error();
    }

    /**
     * 讲师列表分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("{page}/{limit}")
    @ApiOperation(value = "分页获取讲师列表")
    public R pageList(@ApiParam(name = "page", value = "当前页码", required = true)
                          @PathVariable("page") Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                      @PathVariable("limit") Long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        IPage<EduTeacher> res = eduTeacherService.page(pageTeacher, null);
        return R.ok().data("total", res.getTotal()).data("rows", res.getRecords());
    }

    /**
     * 多条件分页获取讲师列表
     * @param page
     * @param limit
     * @param teacherQuery
     * @return
     */
    @PostMapping("pageCondition/{page}/{limit}")
    @ApiOperation(value = "多条件分页获取讲师列表")
    public R pageCondition(@ApiParam(name = "page",value = "当前页码",required = true) @PathVariable("page") Long page,
                           @ApiParam(name = "limit",value = "每页记录数",required = true) @PathVariable("limit") Long limit,
                           @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<EduTeacher>();
        if(teacherQuery == null){
            return pageList(page,limit);
        }
        if(StringUtils.isNotBlank(teacherQuery.getName())){
            wrapper.like("name",teacherQuery.getName());
        }
        if(teacherQuery.getLevel() != null){
            wrapper.eq("level",teacherQuery.getLevel());
        }
        if(StringUtils.isNotBlank(teacherQuery.getBegin())){
            wrapper.ge("gmt_create",teacherQuery.getBegin());
        }
        if(StringUtils.isNotBlank(teacherQuery.getEnd())){
            wrapper.le("gmt_create",teacherQuery.getEnd());
        }
        IPage<EduTeacher> res = eduTeacherService.page(teacherPage, wrapper);
        return R.ok().data("total",res.getTotal()).data("rows",res.getRecords());
    }
}

