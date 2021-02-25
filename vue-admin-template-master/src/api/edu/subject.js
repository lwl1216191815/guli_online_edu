import request from '@/utils/request'

export default {
  /**
   * 获取课程分类树
   */
  getSubjectTree() {
    return request({
      url: `eduservice/edu-subject/getAllSubject`,
      method: 'get',
    })
  },
}
