package com.atguigu.educms.controller;


import com.atguigu.common.utils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author liuwenlong
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/eduCms/crm-banner/admin")
@Api(tags="banner后台管理")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    /**
     * 获取banner的分页列表
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("获取banner分页列表")
    @GetMapping("/pageList/{page}/{limit}")
    public R pageList(@PathVariable("page") @ApiParam(name = "page", value = "当前页码", required = true) Long page,
                      @PathVariable("limit") @ApiParam(name = "limit", value = "每页记录数", required = true) Long limit) {
        Page<CrmBanner> res = new Page<>(page, limit);
        crmBannerService.page(res, null);
        return R.ok().data("items", res.getRecords()).data("total", res.getTotal());
    }

    /**
     * 新增banner
     * @param banner
     * @return
     */
    @ApiOperation("新增banner")
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner banner) {
        crmBannerService.save(banner);
        return R.ok();
    }

    /**
     * 根据ID修改banner
     * @param banner
     * @return
     */
    @ApiOperation("根据bannerID修改banner")
    @PutMapping("/updateBanner")
    public R updateBanner(@RequestBody CrmBanner banner){
        crmBannerService.updateById(banner);
        return R.ok();
    }

    /**
     * 根据ID删除banner
     * @param bannerId
     * @return
     */
    @ApiOperation("根据ID删除banner")
    @DeleteMapping("/{bannerId}")
    public R deleteBanner(@PathVariable("bannerId") @ApiParam(name = "bannerId",value = "需要删除的banner主键",required = true) String bannerId){
        crmBannerService.removeById(bannerId);
        return R.ok();
    }

}

