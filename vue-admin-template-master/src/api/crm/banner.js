import request from '@/utils/request';

export default {
  /**
   * 分页查询banner
   * @param page
   * @param limit
   */
  getPageList(page,limit){
    return request({
      url:`eduCms/crm-banner/admin/pageList/${page}/${limit}`,
      method:'get'
    });
  },
  /**
   * 添加轮播图
   * @param banner
   */
  addBanner(banner){
    return request({
      url:'eduCms/crm-banner/admin/addBanner',
      method:'post',
      data:banner
    });
  },
  /**
   * 根据ID获取banner
   * @param bannerId
   */
  getBannerById(bannerId){
    return request({
      url:`eduCms/crm-banner/admin/${bannerId}`,
      method:'get'
    });
  },
  /**
   * 修改banner
   * @param banner
   */
  updateBanner(banner){
    return request({
      url:'eduCms/crm-banner/admin/updateBanner',
      method:'put',
      data:banner
    });
  }
}
