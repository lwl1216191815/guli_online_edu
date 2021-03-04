import request from '@/utils/request'

export default {
  /**
   * 根据小节ID获取课程小节
   * @param videoId 课时ID
   */
  getVideoById(videoId) {
    return request({
      url: `eduservice/edu-video/${videoId}`,
      method: 'get',
    });
  },
  /**
   * 添加课时（课程小节）
   * @param video 课程小节
   */
  addVideo(video) {
    return request({
      url: `eduservice/edu-video/addVideo`,
      method: 'post',
      data: video
    });
  },
  /**
   * 根据课时ID删除课时
   * @param videoId 章节ID
   */
  removeVideo(videoId) {
    return request({
      url: `eduservice/edu-video/${videoId}`,
      method: 'delete',
    });
  },
  /**
   * 修改课时
   * @param video 课时
   */
  editVideo(video) {
    return request({
      url: `eduservice/edu-video/editVideo`,
      method: 'put',
      data: video
    });
  },
}
