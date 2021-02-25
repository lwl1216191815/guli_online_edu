<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="信息描述">
        <el-tag type="info">excel模版说明</el-tag>
        <el-tag>
          <i class="el-icon-download"/>
          <a :href="'/static/01.xlsx'">点击下载模版</a>
        </el-tag>
      </el-form-item>
      <el-form-item label="选择Excel">
        <el-upload
          ref="upload"
          :auto-upload="false"
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          :disabled="importBtnDisabled"
          :limit="1"
          :action="BASE_API+'/eduservice/edu-subject/importSubject'"
          name="file"
          accept="application/vnd.ms-excel">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button
            :loading="loading"
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload">{{ fileUploadBtnText }}</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        BASE_API: process.env.BASE_API, // 接口API地址
        fileUploadBtnText: '上传到服务器', // 按钮文字
        importBtnDisabled: false, // 按钮是否禁用,
        loading: false
      }
    },
    created(){

    },
    methods:{
      /**
       * 点击按钮，将文件上传到接口中
       */
      submitUpload(){
        this.importBtnDisabled = true;
        this.loading = true;
        this.$refs.upload.submit();
      },
      /**
       * 文件上传成功的回调方法
       */
      fileUploadSuccess(){
        this.loading = false;
        this.$message({
          type:'success',
          message:'上传课程分类成功'
        });
      },
      /**
       * 文件上传失败的回调方法
       */
      fileUploadError(){
        this.loading = false;
        this.$message({
          type:'error',
          message:'上传课程分类失败'
        });
        this.$router.push({path:''})
      }

    }
  }
</script>
