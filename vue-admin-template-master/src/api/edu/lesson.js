import request from '@/utils/request'

export default {
  /**
   * 根据小节ID获取课程小节
   * @param lessonId 课时ID
   */
  getLessonById(lessonId) {
    return request({
      url: `eduservice/edu-lesson/${lessonId}`,
      method: 'get',
    });
  },
  /**
   * 添加课时（课程小节）
   * @param lesson 课程小节
   */
  addLesson(lesson) {
    return request({
      url: `eduservice/edu-lesson/addLesson`,
      method: 'post',
      data: lesson
    });
  },
  /**
   * 根据课时ID删除课时
   * @param lessonId 章节ID
   */
  removeLesson(lessonId) {
    return request({
      url: `eduservice/edu-lesson/${lessonId}`,
      method: 'delete',
    });
  },
  /**
   * 修改课时
   * @param lesson 课时
   */
  editLesson(lesson) {
    return request({
      url: `eduservice/edu-lesson/editLesson`,
      method: 'put',
      data: video
    });
  },
  /**
   * 删除阿里云的视屏
   */
  removeALiYunVideo(videoId){
    return request({
      url: `eduVod/vod/${videoId}`,
      method: 'delete',
    });
  }
}
