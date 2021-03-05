<template>
  <div id="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程名称" />
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="courseQuery.status"
          clearable
          placeholder="课程状态">
          <el-option :value="Normal" label="已发布" />
          <el-option :value="Draft" label="未发布" />
        </el-select>
      </el-form-item>
      <el-form-item label="课程讲师">
        <el-select
          v-model="teacherQuery.teacherId"
          clearable placeholder="课程讲师">
          <el-option v-for="teacher in teacherList" :key="teahcer.id" :label="teacher.name" :value="teacher.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="课程一级分类">
        <el-select
          v-model="teacherQuery.subjectParentId"
          clearable placeholder="课程讲师">
          <el-option v-for="subject in subjectParentList" :key="subject.id" :label="subject.label" :value="subject.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="课程二级分类">
        <el-select
          v-model="teacherQuery.subjectId"
          clearable placeholder="课程讲师">
          <el-option v-for="subject in subjectList" :key="subject.id" :label="subject.label" :value="subject.id" />
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row>
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="课程名称" width="80" />
      <el-table-column label="课程状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === 'Normal' ? "已发布" : "未发布" }}
        </template>
      </el-table-column>
      <el-table-column prop="subjectParentName" label="课程一级分类" />
      <el-table-column prop="subjectName" label="课程二级分类" />
      <el-table-column prop="lessonNum" label="课时数" />
      <el-table-column prop="price" label="课程价格" />
      <el-table-column prop="viewCount" label="浏览量" />
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)">删除</el-button>
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
    data() {
      return {
        page: 1,
        limit: 10,
        courseQuery: {
          title:'',
          teacherId:'',
          subjectParentId:'',
          subjectId:''
        },
        list: null,
        total: 0,
        listLoading: false,
        teacherList:[],
        subjectParentList:[],
        subjectList:[]
      };
    },
    created() {
      this.getList();
    },
    methods: {
      getList(page = 1) {
        this.page = page;
        this.listLoading = true;
        teacher.getTeacherListPage(this.page, this.limit, this.courseQuery)
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
        this.courseQuery = {
          title:'',
          teacherId:'',
          subjectParentId:'',
          subjectId:''
        };
        this.getList();
      },
      removeDataById(id) {
        this.$confirm("是否永久删除该课程", "提示", {
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
