import request from '@/utils/request';
export default {
  /**
   * 获取热门讲师和热门课程
   */
  getPopularTeacherAndCourse(){
    return request({
      url:'/eduservice/indexFront/index',
      method:'get'
    });
  }
}
