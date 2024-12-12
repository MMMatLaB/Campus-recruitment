
<script>
import api from "@/api";
import {ElMessage} from "element-plus";

export default {
  data()
  {
    return {
      title: '',
      tableData: [],
      loading:false,
      // tableData: [],
      currentPage: 1,
      pageSize: 6,
      totalItems: 0// 你的数据总条数
    }
  },
  mounted()
  {
    // this.handleSearch();
    this.loadTableData()
  },
  methods:{
    edit(row){
      // ElMessage.warning('正在修改状态，请稍后  '+row.state);
      api.put('/questionnaire/'+row.idquestionnaire, null, {
        params: {
          status: row.state===1?1:0,
        }
      }).then(res => {
        this.$message({
          type: 'success',
          message: '修改成功!'
        }).then(r=>{
          this.loadTableData();
        })

      })

    },
    del(row){
      this.$confirm('此操作将永久删除该问卷, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        api.delete('/questionnaire/'+row.idquestionnaire).then(res => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.loadTableData();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    loadTableData(){
      this.loading=true
      api.get('/questionnaires', {
        params: {
          title: this.title,
        }
      }).then(res => {
        this.tableData = res;
        this.totalItems = res.length;
      })
      this.loading = false
      // setTimeout(() => {
      //
      // }, 500)
      // // this.loading = false
    },
    handleSearch(){
      this.loadTableData();
    },
    handlePreview(fileName){
      this.$router.push({path: '/questionnaire_management/preview/'+fileName})
      // window.open('http://localhost:3000/preview/'+fileName);
    },
    handleCurrentChange(val) {
      this.loading=true
      this.currentPage = val;
    }
  }, computed: {
    currentPageData() {
      setTimeout(()=>{
        this.loading=false
      },500);
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.tableData.slice(start, end);
    }
  }
}
</script>
<template>
  <div>
    <PageHeader>
      <template #title>
        问卷管理
      </template>
    </PageHeader>
    <PageMain>
      <template #title>
        <el-form :inline="true"  style="width: 1500px;">
          <el-form-item label="问卷标题"  >
            <el-input v-model="title" placeholder="问卷标题，模糊查询"/>
          </el-form-item>
          <el-form-item>
            <el-button  @click="handleSearch">查询</el-button>
          </el-form-item>
        </el-form>
        <!--      <el-button type="primary" @click="addQuestionnaire">添加问卷</el-button>-->
      </template>
      <template #default>
        <el-table :data="currentPageData" style="width: 100%" v-loading="loading">
          <el-table-column type="index" label="序号" width="80"></el-table-column>
          <el-table-column
              prop="title"
              label="问卷标题"
              width="180">
          </el-table-column>
          <el-table-column
              prop="intro"
              label="问卷描述"
              width="300"
              :show-overflow-tooltip="true"
          >
          </el-table-column>
          <el-table-column
              prop="creattime"
              label="创建时间"
              width="180">
          </el-table-column>
          <el-table-column
              prop="status"
              label="是否发布"
              width="180">
            <template #default="scope">
              <el-switch
                  @change="edit(scope.row)"
                  v-model="scope.row.state"
                  active-color="#409eff"
                  inactive-color="gray"
                  :active-value="1"
                  :inactive-value="0"
                  inline-prompt
                  active-text="是"
                  inactive-text="否">
              </el-switch>
            </template>
          </el-table-column>


          <el-table-column
              label="操作"
              width="180">
            <template #default="scope">
              <el-space alignment="center">


              <el-button type="primary" link  @click="handlePreview(scope.row.fileName) ">
                <SvgIcon name="ion:eye-outline"/>
                查看</el-button>
              <el-button type="danger" link  @click="del(scope.row)">
                <!--            <SvgIcon name="material-symbols-light:delete-outline" />-->
                <SvgIcon name="material-symbols-light:delete-outline" />
                删除</el-button>
              </el-space>
            </template>
          </el-table-column>
        </el-table>
        <el-divider direction="horizontal" border-style="hidden"
        ></el-divider>
        <el-pagination
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 50]"
            :page-size="pageSize"
            :total="totalItems"
        ></el-pagination>
      </template>
    </PageMain>
  </div>
</template>

<style scoped lang="scss">

</style>
