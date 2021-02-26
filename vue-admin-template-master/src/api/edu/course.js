import request from '@/utils/request'

export default {
  /**
   * 添加课程
   */
  addCourse(courseInfo) {
    return request({
      url: `eduservice/edu-course`,
      method: 'post',
      data: courseInfo
    });
  },
}
