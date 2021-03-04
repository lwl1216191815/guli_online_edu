package com.atguigu.eduservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频（章节中的每一小节） 前端控制器
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
@Api(tags = "课时前端控制器")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 添加课程小节
     * @param eduVideo
     * @return
     */
    @ApiOperation("添加课时（课程小节）")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    /**
     * 修改课程小节
     * @param video
     * @return
     */
    @PutMapping("/editVideo")
    @ApiOperation("修改课程小节（课时）")
    public R modifyVideo(@RequestBody EduVideo video){
        eduVideoService.updateById(video);
        return R.ok();
    }

    /**
     * 根据ID获取课时详情
     * @param videoId
     * @return
     */
    @GetMapping("{videoId}")
    @ApiOperation("根据ID获取课时详情")
    public R getVideoById(@PathVariable String videoId){
        EduVideo video = eduVideoService.getById(videoId);
        return R.ok().data("video",video);
    }

    /**
     * 根据ID删除课程小节
     * @param videoId
     * @return
     */
    @DeleteMapping("{videoId}")
    @ApiOperation("根据ID删除课时")
    public R removeVideoById(@PathVariable String videoId){
        return R.ok();
    }
}

