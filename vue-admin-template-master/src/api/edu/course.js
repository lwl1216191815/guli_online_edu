import request from '@/utils/request'

export default {
  /**
   * 添加课程
   */
  addCourse(courseInfo) {
    return request({
      url: `eduservice/edu-course/addCourse`,
      method: 'post',
      data: courseInfo
    });
  },
  /**
   * 根据ID获取课程信息
   * @param courseId
   */
  getCourseInfoById(courseId){
    return request({
      url: `eduservice/edu-course/getCourseById/${courseId}`,
      method: 'get'
    });
  },
  /**
   * 修改课程信息
   * @param courseInfo 课程信息
   */
  updateCourse(courseInfo){
    return request({
      url: `eduservice/edu-course/updateCourse`,
      method: 'put',
      data:courseInfo
    });
  },
  /**
   * 获取课程发布信息
   * @param courseId 课程ID
   */
  getCoursePublishInfo(courseId){
    return request({
      url: `eduservice/edu-course/getCoursePublishInfo/${courseId}`,
      method: 'get'
    });
  },
  /**
   * 发布课程
   * @param courseId 课程ID
   */
  publish(courseId){
    return request({
      url: `eduservice/edu-course/publish/${courseId}`,
      method: 'put'
    });
  },
  /**
   * 按照条件分页获取课程列表
   * @param courseQuery 查询条件
   * @param limit 每页的记录数
   * @param page 当前页码
   */
  getPageList(courseQuery,limit,page){
    return request({
      url: `eduservice/edu-course/getPageList/${limit}/${page}`,
      method: 'post',
      data:courseQuery
    });
  },
  /**
   * 根据ID删除课程
   * @param courseId
   */
  removeCourseById(courseId){
    return request({
      url: `eduservice/edu-course/${courseId}/`,
      method: 'delete',
    });
  }
}
