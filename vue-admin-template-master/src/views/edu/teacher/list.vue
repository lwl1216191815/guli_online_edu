<template>
  <div id="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="讲师名" />
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="teacherQuery.level"
          clearable
          placeholder="讲师头衔"
        >
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>
      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()"
        >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="80" />
      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === 1 ? "高级讲师" : "首席讲师" }}
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="资历" />
      <el-table-column prop="gmtCreate" label="添加时间" width="160" />
      <el-table-column prop="sort" label="排序" width="60" />
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :total="total"
      :current-page="page"
      :page-size="limit"
      layout="total,prev,pager,next,jumper"
      style="padding: 30px 0; text-align: center"
      @current-change="getList"
    ></el-pagination>
  </div>
</template>
<script>
import teacher from "@/api/edu/teacher";
export default {
  //定义全局变量和初始值
  data() {
    return {
      page: 1,
      limit: 10,
      teacherQuery: {},
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
      teacher.getTeacherListPage(this.page, this.limit, this.teacherQuery)
        .then((response) => {
          this.list = response.data.rows;
          this.total = response.data.total;
          this.listLoading = false;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    resetData() {
      this.teacherQuery = {};
      this.getList();
    },
    removeDataById(id) {
      this.$confirm("是否删除该讲师", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        teacher.deleteTeacherId(id).then((res) => {
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