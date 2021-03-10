<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>
    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
      </el-form-item>
      <!-- 所属分类：级联下拉列表 -->
      <!-- 一级分类 -->
      <el-form-item label="课程类别">
        <el-select
          v-model="courseInfo.subjectParentId"
          placeholder="请选择一级分类" @change="changeParent">
          <el-option
            v-for="subject in parentSubjectList"
            :key="subject.id"
            :label="subject.label"
            :value="subject.id"/>
        </el-select>
        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="请选择二级分类">
          <el-option
            v-for="subject in subjectList"
            :key="subject.value"
            :label="subject.label"
            :value="subject.id"/>
        </el-select>
      </el-form-item>
      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select
          v-model="courseInfo.teacherId"
          placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
      </el-form-item>
      <el-form-item label="课程简介">
        <tinymce v-model="courseInfo.description" :height="300"/>
      </el-form-item>

      <!-- 课程封面-->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API+'/eduOss/fileOss'"
          class="avatar-uploader">
          <img :src="courseInfo.cover">
        </el-upload>
      </el-form-item>
      <el-form-item label="课程价格">
        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import courseApi from '@/api/edu/course';
  import teacherApi from '@/api/edu/teacher';
  import subjectApi from '@/api/edu/subject';
  import Tinymce from '@/components/Tinymce';
  export default {
    components:{ Tinymce },
    data() {
      return {
        saveBtnDisabled: false, // 保存按钮是否禁用
        courseInfo:{
          cover:'/static/01.jpg',
          id:''
        },
        teacherList:[],
        parentSubjectList:[],
        subjectList:[],
        BASE_API:process.env.BASE_API,
      }
    },
    created() {
      this.init();
    },
    watch:{
      $route(to,from){
        this.init();
      }
    },
    methods: {
      saveOrUpdate(){
        if(this.courseInfo.id){
          this.editCourse();
        }else{
          this.addCourse();
        }
      },
      next(id) {
        this.$router.push({path: `/course/chapter/${id}`})
      },
      /**
       * 获取所有讲师
       */
      getAllTeacher(){
        teacherApi.getAllTeacher().then(
          response => {
            this.teacherList = response.data.items;
          }
        ).catch();
      },
      /**
       * 获取课程分类列表
       */
      getSubjectList(){
        subjectApi.getSubjectTree().then(
          response => {
            this.parentSubjectList = response.data.list;
            if(!this.courseInfo.id){
              this.subjectList = this.parentSubjectList[0].children;
              this.courseInfo.subjectParentId=this.parentSubjectList[0].id;
              this.courseInfo.subjectId = this.subjectList[0].id;
            }else{
              for(let i = 0; i < this.parentSubjectList.length; i++){
                if(this.parentSubjectList[i].id === this.courseInfo.subjectParentId){
                  this.subjectList = this.parentSubjectList[i].children;
                  break;
                }
              }
            }
          }
        ).catch();
      },
      /**
       * 一级分类改变后的事件
       */
      changeParent(value){
        for(let i=0; i < this.parentSubjectList.length; i++){
          if(value === this.parentSubjectList[i].id){
            this.subjectList = this.parentSubjectList[i].children;
            this.courseInfo.subjectId = this.subjectList[0].id;
          }
        }
      },
      /**
       * 完成上传之后的调用
       */
      handleAvatarSuccess(res,file){
        this.courseInfo.cover = res.data.url;
      },
      /**
       * 上传前调用
       */
      beforeAvatarUpload(file){
        const isJpeg = file.type === 'image/jpeg';
        const isJpg = file.type === 'image/jpg';
        const isPng = file.type === 'image/png';
        const isGif = file.type === 'image/gif';

        const isLt2m = file.size / 1024 /1024 < 2;

        if(!isJpeg && !isJpg && !isPng && !isGif){
          this.$message.error('封面只能上传jpg、png、jpeg、gif格式的图片');
        }

        if(!isLt2m){
          this.$message.error('封面大小不能大于2m');
        }
      },
      getCourseInfo(courseId){
        courseApi.getCourseInfoById(courseId).then(
          response => {
            this.courseInfo = response.data.data;
          }
        ).catch();
      },
      init(){
        this.getAllTeacher();
        this.getSubjectList();
        if(this.$route.params && this.$route.params.id){
          this.courseInfo.id = this.$route.params.id;
          this.getCourseInfo(this.courseInfo.id);
        }else {
          this.courseInfo = {
            cover:'/static/01.jpg',
            id:''
          }
        }
      },
      /**
       * 添加课程
       */
      addCourse(){
        courseApi.addCourse(this.courseInfo).then(
          response => {
            this.$message({
              type:'success',
              message:'添加课程成功'
            });
            this.next(response.data.courseId);
          }
        ).catch();
      },
      /**
       * 修改课程信息
       */
      editCourse(){
        courseApi.updateCourse(this.courseInfo).then(
          response => {
            this.$message({
              type:'success',
              message:'修改课程成功'
            });
            this.next(this.courseInfo.id);
          }
        ).catch();
      }
    }
  }
</script>
<style scoped>
  .tinymce-container {
    line-height: 29px;
  }
</style>
