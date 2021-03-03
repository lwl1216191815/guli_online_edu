import request from '@/utils/request'

export default {
  /**
   * 根据课程ID获取章节和小节
   * @param courseId 章节ID
   */
  getChapterVideoTree(courseId) {
    return request({
      url: `eduservice/edu-chapter/getChapterVideo/${courseId}`,
      method: 'get',
    });
  },
  /**
   * 根据章节ID获取课程章节
   * @param chapterId 章节ID
   */
  getChapterById(chapterId) {
    return request({
      url: `eduservice/edu-chapter/${chapterId}`,
      method: 'get',
    });
  },
  /**
   * 添加章节
   * @param chapter 章节信息
   */
  addChapter(chapter) {
    return request({
      url: `eduservice/edu-chapter/addChapter`,
      method: 'post',
      data: chapter
    });
  },
  /**
   * 根据章节ID删除章节
   * @param chapterId 章节ID
   */
  removeChapter(chapterId) {
    return request({
      url: `eduservice/edu-chapter/${chapterId}`,
      method: 'delete',
    });
  },
  /**
   * 根据章节ID删除章节
   * @param chapterId 章节ID
   */
  editChapter(chapter) {
    return request({
      url: `eduservice/edu-chapter/editChapter`,
      method: 'put',
      data: chapter
    });
  },
}
