package com.atguigu.educms.service;

import com.atguigu.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-04-08
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 获取banner列表
     * @return
     */
    List<CrmBanner> getBannerList();
}
