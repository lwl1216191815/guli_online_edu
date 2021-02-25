<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />

    <el-tree
      ref="tree2"
      :data="data2"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />

  </div>
</template>

<script>
  import subjectApi from '@/api/edu/subject';
  export default {
    data() {
      return {
        filterText: '',
        data2: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    watch: {
      filterText(val) {
        this.$refs.tree2.filter(val)
      }
    },
    created(){
      this.getSubjectTree();
    },
    methods: {
      filterNode(value, data) {
        if (!value) return true
        return data.label.toLowerCase().indexOf(value.toLowerCase()) !== -1
      },
      /**
       * 获取课程分类树
       */
      getSubjectTree(){
        subjectApi.getSubjectTree().then(
          response => {
            this.data2 = response.data.list;
          }
        ).catch();
      }
    }
  }
</script>

