<template>
  <div id="app-container">
    <el-table v-loading="listLoading" :data="list" element-loading-text="数据加载中" border fit highlight-current-row>
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="标题" width="80" />
      <el-table-column prop="linkUrl" label="链接地址" />
      <el-table-column prop="gmtCreate" label="添加时间" width="160" />
      <el-table-column prop="sort" label="排序" width="60" />
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/banner/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination :total="total" :current-page="page" :page-size="limit" layout="total,prev,pager,next,jumper" style="padding: 30px 0; text-align: center" @current-change="getList"></el-pagination>
  </div>
</template>
<script>
  import bannerApi from "@/api/crm/banner";
  export default {
    //定义全局变量和初始值
    data() {
      return {
        page: 1,
        limit: 10,
        bannerQuery: {},
        list: null,
        total: 0,
        listLoading: false,
      };
    },
    //页面渲染之前执行，一般调用methods里面的方法
    created() {
      this.getList();
    },
    //方法
    methods: {
      getList(page = 1) {
        this.page = page;
        this.listLoading = true;
        bannerApi.getPageList(this.page, this.limit)
          .then((response) => {
            this.list = response.data.items;
            this.total = response.data.total;
            this.listLoading = false;
          }).catch((error) => {
            console.log(error);
          });
      },
      removeDataById(id) {
        this.$confirm("是否删除该讲师", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          bannerApi.deleteBannerById(id).then((res) => {
            this.$message({
              type: "success",
              message: "删除成功",
            });
            this.getList();
          }).catch((error) => {});
        });
      },
    },
  };
</script>
