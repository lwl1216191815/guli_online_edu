<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>
    <el-button type="text" @click="openChapterDialog()">添加章节</el-button>

    <!-- 章节列表 -->
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

        <!-- 小节列表 -->
        <ul class="chanpterList videoList">
          <li
            v-for="video in chapter.children"
            :key="video.id">
            <p>{{ video.title }}
              <span class="acts">
                    <el-button style="" type="text" @click="openEditVideo(video.id)">编辑</el-button>
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

    <!-- 添加和修改课时表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.isFree">
            <el-radio :label="true">免费</el-radio>
            <el-radio :label="false">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <!-- TODO -->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import chapterApi from '@/api/edu/chapter';
  import videoApi from '@/api/edu/video';
  export default {
    data() {
      return {
        saveBtnDisabled: false, // 保存按钮是否禁用
        saveVideoBtnDisabled:false,//保存小节按钮是否禁用
        chapterVideoList:[],
        dialogChapterFormVisible:false,//弹出框是否显示
        dialogVideoFormVisible:false,//小节弹出框是否显示
        chapter:{
          title:'',
          sort:0
        },
        video:{
          title:'',
          sort:0,
          isFree:null,
        }
      }
    },
    created() {
      if(this.$route.params && this.$route.params.id){
        this.chapter.courseId = this.$route.params.id;
        this.video.courseId = this.$route.params.id;
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
       * 添加章节，打开modal对话框
       */
      openChapterDialog(){
        this.dialogChapterFormVisible = true;
        this.chapter.title = '';
        this.chapter.sort = 0;
      },
      /**
       * 删除小节
       */
      removeVideo(videoId){
        this.$confirm("是否永久删除该课时", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          videoApi.removeVideo(videoId).then(
            response => {
              this.$message({
                type: "success",
                message: "删除成功",
              });
              this.getChapterVideo(this.chapter.courseId);
            }
          ).catch((error) => {});
        });
      },
      /**
       * 打开小节
       */
      openVideo(chapterId){
        this.video.chapterId = chapterId;
        this.dialogVideoFormVisible = true;
        this.video.courseId = this.chapter.courseId;
      },
      /**
       * 打开编辑章节弹框,并且回显章节表单
       */
      openEditChapter(chapterId){
        this.dialogChapterFormVisible = true;
        chapterApi.getChapterById(chapterId).then(
          response => {
            this.chapter = response.data.chapter;
          }
        ).catch();
      },
      /**
       * 根据ID删除章节
       */
      removeChapter(chapterId){
        this.$confirm("是否永久删除该章节", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          chapterApi.removeChapter(chapterId).then(
            response => {
              this.$message({
                type: "success",
                message: "删除成功",
              });
              this.getChapterVideo(this.chapter.courseId);
            }
          ).catch((error) => {});
        });
      },
      saveOrUpdate(){
        if(this.chapter.id){
          this.updateChapter();
        }else {
          this.addChapter();
        }
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
      },
      /**
       * 修改课程章节
       */
      updateChapter(){
        chapterApi.editChapter(this.chapter).then(
          response => {
            this.dialogChapterFormVisible = false;
            this.$message({type:'success',message:'修改课程章节成功'});
            this.getChapterVideo(this.chapter.courseId);
          }
        ).catch();
      },
      editVideo(){
        videoApi.editVideo(this.video).then(
          response => {
            //关闭弹窗
            this.dialogVideoFormVisible = false;
            //提示信息
            this.$message({type:'success',message:'修改小节成功'});
            //刷新页面
            this.getChapterVideo(this.video.courseId);
            //清空video
            this.clearVideo();
          }
        ).catch();
      },
      /**
       * 保存或者修改小节
       */
      saveOrUpdateVideo(){
        if(this.video.id){
          this.editVideo();
        }else {
          this.addVideo();
        }
      },
      /**
       * 添加课时
       */
      addVideo(){
        videoApi.addVideo(this.video).then(
          response => {
            this.dialogVideoFormVisible = false;//关闭弹窗
            //提示信息
            this.$message({type:'success',message:'添加课时成功'});
            //刷新列表
            this.getChapterVideo(this.video.courseId);
            //清空video
            this.clearVideo();
          }
        ).catch();
      },
      clearVideo(){
        this.video.title = '';
        this.video.sort = 0;
        this.video.isFree = null;
        this.video.chapterId = '';
      },
      /**
       * 打开video编辑信息
       * @param video
       */
      openEditVideo(videoId){
        this.dialogVideoFormVisible = true;
        videoApi.getVideoById(videoId).then(
          response => {
            this.video = response.data.video;
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
    z-index: 1;
  }
  .chanpterList p{
    position: relative;
    float: left;
    font-size: 20px;
    margin: 10px 0;
    padding: 10px;
    height: 70px;
    line-height: 50px;
    width: 100%;
    border: 1px solid #DDD;
    z-index: 2;
  }
  .chanpterList .acts {
    position: relative;
    float: right;
    font-size: 14px;
    z-index: 3;
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
