import request from '@/utils/request';

export default {
  /**
   * 分页查询banner
   * @param page
   * @param limit
   */
  getBannerList(){
    return request({
      url:`/eduCms/crm-banner/front`,
      method:'get'
    });
  },
}
