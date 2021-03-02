import request from '@/utils/request'

export default {
  /**
   * 添加课程
   */
  getChapterVideoTree(courseId) {
    return request({
      url: `eduservice/edu-chapter/getChapterVideo/${courseId}`,
      method: 'get',
    });
  },
}
