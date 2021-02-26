import request from '@/utils/request'

export default{
    /**
     * 查询讲师列表
     * @param {*} page 当前页码 
     * @param {*} limit 每页记录数 
     * @param {*} teacherQuery 查询条件
     */
    getTeacherListPage(page,limit,teacherQuery){
        return request({
            url: `eduservice/edu-teacher/pageCondition/${page}/${limit}`,
            method: 'post',
            data: teacherQuery
          })
    },
    /**
     * 根据ID删除讲师
     * @param {*} id 讲师的ID
     */
    deleteTeacherId(id){
        return request({
            url:`eduservice/edu-teacher/${id}`,
            method:'delete'
        });
    },
    /**
     * 保存讲师的方法
     * @param {*} teacher 讲师对象
     */
    addTeacher(teacher){
        return request({
            url:`eduservice/edu-teacher/addTeacher`,
            method:'post',
            data:teacher
        });
    },
  /**
   * 根据ID获取讲师信息
   * @param id
   */
  getTeacherInfo(id){
    return request({
      url:`eduservice/edu-teacher/${id}`,
      method:'get'
    });
  },
  /**
   * 修改讲师
   * @param teacher
   */
  updateTeacher(teacher){
    return request({
      url:`eduservice/edu-teacher/${teacher.id}`,
      method:'put',
      data:teacher
    });
  },
  /**
   * 获取所有讲师
   */
  getAllTeacher(){
    return request({
      url:`eduservice/edu-teacher`,
      method:'get',
    });
  }
}
