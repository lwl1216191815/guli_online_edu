<template>
  <div id="app-container">
    <el-form label-width="120px">
      <el-form-item label="轮播图标题">
        <el-input v-model="banner.title" />
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="banner.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="链接地址">
        <el-input v-model="banner.linkUrl" />
      </el-form-item>

      <el-form-item label="轮播图">
        <pan-thumb :image="banner.imageUrl"/>
        <el-button type="primary" icon="el-icon-upload" @click="imageCropperShow=true">更换头像
        </el-button>
        <!--
    v-show：是否显示上传组件
    :key：类似于id，如果一个页面多个图片上传控件，可以做区分
    :url：后台上传的url地址
    @close：关闭上传组件
    @crop-upload-success：上传成功后的回调 -->
        <image-cropper
          v-show="imageCropperShow"
          :width="300"
          :height="300"
          :key="imageCropperKey"
          :url="BASE_API+'/eduOss/fileOss'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"/>
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import bannerApi from '@/api/crm/banner';
  import ImageCropper from '@/components/ImageCropper';
  import PanThumb from '@/components/PanThumb';
  export default {
    components: { ImageCropper, PanThumb },
    data() {
      return {
        banner:{},
        saveBtnDisabled:false,//保存按钮是否禁用
        imageCropperShow:false,//上传弹框组件是否显示
        BASE_API:process.env.BASE_API,
        imageCropperKey:0,//上传组件key值
      }
    },
    created(){
      this.init();
    },
    watch:{
      $route(to,from){
        this.init();
      }
    },
    methods:{
      init(){
        if(this.$route.params && this.$route.params.id){
          const id = this.$route.params.id;
          this.getInfo(id);
        }else{
          this.banner = {};
        }
      },
      saveOrUpdate(){
        if(this.banner.id){
          this.updateBanner();
        }else{
          this.saveBanner();
        }
      },
      /**
       * 新增轮播图
       * @param id
       */
      saveBanner(){
        bannerApi.addBanner(this.banner).then(
          response => {
            this.$message({type:'success',message:'添加成功'});
            this.$router.push({path:'/banner/list'});
          }
        )
      },
      /**
       * 回显讲师信息
       * @param id
       */
      getInfo(id){
        bannerApi.getBannerById(id).then(
          response => {
            this.banner = response.data.item;
          }
        )
      },
      /**
       * 修改讲师的方法
       */
      updateBanner(){
        bannerApi.updateBanner(this.banner).then(
          response => {
            this.$message({type:'success',message:'修改成功'});
            this.$router.push({path:'/banner/list'});
          }
        );
      },
      /**
       * 关闭上传弹窗的方法
       */
      close(){
        this.imageCropperShow = false;
        this.imageCropperKey += 1;
      },
      /**
       * 上传成功后的执行方法
       */
      cropSuccess(data){
        this.close();
        this.banner.imageUrl = data.url;
      }
    }
  }
</script>
