<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>
    <el-button type="text" @click="openChapterDialog()">添加章节</el-button>

    <!-- 章节 -->
    <ul class="chanpterList">
      <li
        v-for="chapter in chapterVideoList"
        :key="chapter.id">
        <p>
          {{ chapter.title }}

          <span class="acts">
                    <el-button style="" type="text" @click="openVideo(chapter.id)">添加小节</el-button>
                    <el-button style="" type="text" @click="openEditChatper(chapter.id)">编辑</el-button>
                    <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
                </span>
        </p>

        <!-- 视频 -->
        <ul class="chanpterList videoList">
          <li
            v-for="video in chapter.children"
            :key="video.id">
            <p>{{ video.title }}

              <span class="acts">

                    <el-button style="" type="text">编辑</el-button>
                    <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
                </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>
    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import chapterApi from '@/api/edu/chapter'
  export default {
    data() {
      return {
        saveBtnDisabled: false, // 保存按钮是否禁用
        courseId:'',
        chapterVideoList:[],
      }
    },
    created() {
      if(this.$router.params && this.$router.params.id){
        this.courseId = this.$router.params.id;
        this.getChapterVideo(this.courseId);
      }
    },
    methods: {
      previous() {
        console.log('previous')
        this.$router.push({path: '/edu/course/info/1'})
      },
      next() {
        console.log('next')
        this.$router.push({path: '/edu/course/publish/1'})
      },
      getChapterVideo(courseId){
        chapterApi.getChapterVideoTree(courseId).then(
          response => {
            this.chapterVideoList = response.data.list;
          }
        ).catch();
      },
      /**
       * 打开modal对话框
       */
      openChapterDialog(){

      },
      /**
       * 删除小节
       */
      removeVideo(){

      },
      /**
       * 打开小节
       */
      openVideo(){},
      openEditChatper(){},
      removeChapter(){

      }

    }
  }
</script>
