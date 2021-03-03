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
      <li v-for="chapter in chapterVideoList"
        :key="chapter.id">
        <p> {{ chapter.title }}
          <span class="acts">
                    <el-button type="text" @click="openVideo(chapter.id)">添加小节</el-button>
                    <el-button type="text" @click="openEditChapter(chapter.id)">编辑</el-button>
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
    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import chapterApi from '@/api/edu/chapter'
  export default {
    data() {
      return {
        saveBtnDisabled: false, // 保存按钮是否禁用
        chapterVideoList:[],
        dialogChapterFormVisible:false,//弹出框是否显示
        chapter:{
          title:'',
          sort:0
        },
      }
    },
    created() {
      if(this.$route.params && this.$route.params.id){
        this.chapter.courseId = this.$route.params.id;
        this.getChapterVideo(this.chapter.courseId);
      }
    },
    methods: {
      /**
       * 上一页
       */
      previous() {
        this.$router.push({path: `/course/info/${this.chapter.courseId}`})
      },
      /**
       * 下一页
       */
      next() {
        this.$router.push({path: `/course/publish/${this.chapter.courseId}`})
      },
      /**
       * 获取本课程的章节和小节
       */
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
        this.dialogChapterFormVisible = true;
        this.chapter.title = '';
        this.chapter.sort = 0;
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
      /**
       * 打开编辑章节弹框
       */
      openEditChapter(chapter){
        this.dialogChapterFormVisible = true;
        this.chapter = chapter;
      },
      removeChapter(){
        alert(1111);
      },
      saveOrUpdate(){
        this.addChapter();
      },
      /**
       * 添加章节
       */
      addChapter(){
        chapterApi.addChapter(this.chapter).then(
          response => {
            this.dialogChapterFormVisible = false;
            this.$message({type:'success',message:'添加课程章节成功'});
            this.getChapterVideo(this.chapter.courseId);
          }
        ).catch();
      }
    }
  }
</script>
<style scoped>
  .chanpterList{
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
  }
  .chanpterList li{
    position: relative;
  }
  .chanpterList p{
    float: left;
    font-size: 20px;
    margin: 10px 0;
    padding: 10px;
    height: 70px;
    line-height: 50px;
    width: 100%;
    border: 1px solid #DDD;
  }
  .chanpterList .acts {
    float: right;
    font-size: 14px;
  }
  .videoList{
    padding-left: 50px;
  }
  .videoList p{
    float: left;
    font-size: 14px;
    margin: 10px 0;
    padding: 10px;
    height: 50px;
    line-height: 30px;
    width: 100%;
    border: 1px dotted #DDD;
  }
</style>
