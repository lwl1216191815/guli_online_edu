package com.atguigu.educms.controller;

import com.atguigu.common.utils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * banner前台controller
 */
@RestController
@RequestMapping("/eduCms/crm-banner/front")
@Api(tags="banner前台管理")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    /**
     * 获取banner列表
     * @return
     */
    @GetMapping
    @ApiOperation("获取banner")
    public R getBannerList(){
        List<CrmBanner> bannerList = crmBannerService.getBannerList();
        return R.ok().data("items",bannerList);
    }
}
