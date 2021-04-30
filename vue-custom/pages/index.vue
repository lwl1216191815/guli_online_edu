<template>
  <div>
    <!-- 幻灯片 开始 -->
    <swiper ref="mySwiper" :options="swiperOptions">
      <swiper-slide v-for="banner in bannerList" :key="banner.id">
        <a target="_blank" :href="banner.linkUrl">
          <img :src="banner.imageUrl" :alt="banner.title"/>
        </a>
      </swiper-slide>
      <div class="swiper-pagination" slot="pagination"></div>
    </swiper>
    <!-- 幻灯片 结束 -->

    <div id="aCoursesList">
      <!-- 网校课程 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门课程</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="bna">
                <li v-for="course in courseList" :key="course.id">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img :src="course.cover" class="img-responsive" :alt="course.title">
                      <div class="cc-mask">
                        <a href="#" :title="course.title" class="comm-btn c-btn-1">开始学习</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a href="#" :title="course.title" class="course-title fsize18 c-333">{{course.title}}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-green">
                        <i class="c-fff fsize12 f-fA">{{Number(course.price) == 0 ? '免费': course.price}}</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">9634人学习</i>
                        |
                        <i class="c-999 f-fA">9634评论</i>
                      </span>
                    </section>
                  </div>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="#" title="全部课程" class="comm-btn c-btn-2">全部课程</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校课程 结束 -->
      <!-- 网校名师 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">名师大咖</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="teacher in teacherList" :key="teacher.id">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a href="/teacher/1" :title="teacher.name">
                        <img :title="teacher.name" :src="teacher.avatar">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a href="/teacher/1" :title="teacher.name" class="fsize18 c-666">{{teacher.name}}</a>
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{teacher.career}}</span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <p class="c-999 f-fA">{{teacher.intro}}</p>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="#" title="全部讲师" class="comm-btn c-btn-2">全部讲师</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>
<script>
  import bannerApi from '@/api/banner';
  import indexApi from '@/api/index';
  export default {
    data() {
      return {
        swiperOptions: {
          autoplay:true,
          speed:1,
          pagination: {
            el: '.swiper-pagination'
          },
        },
        bannerList:[],
        teacherList:[],
        courseList:[]
      }
    },
    computed: {
      swiper() {
        return this.$refs.mySwiper.swiper
      }
    },
    created(){
      this.getBannerList();
      this.getPopularTeacherAndCourse();
    },
    mounted() {

    },
    methods:{
      /**
       * 获取banner列表
       */
      getBannerList(){
        bannerApi.getBannerList().then(
          response => {
            this.bannerList = response.data.data.items;
          }
        ).catch();
      },
      /**
       * 获取热门讲师和热门课程
       */
      getPopularTeacherAndCourse(){
        indexApi.getPopularTeacherAndCourse().then(
          response => {
            this.teacherList = response.data.data.teacherList;
            this.courseList = response.data.data.courseList;
          }
        ).catch();
      }
    }
  }
</script>
