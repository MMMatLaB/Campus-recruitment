<script setup lang="ts">

import {ref} from "vue";
import {UploadInstance} from "element-plus";

const uploadRef = ref<UploadInstance >()
const submitUpload = () => {
    uploadRef.value!.submit()
}
</script>
<script lang="ts">
import api from "@/api";
// import { ref } from 'vue'
// import { genFileId } from 'element-plus'
// import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'


export default {
  data()
  {
    return {
      target:import.meta.env.VITE_APP_API_BASEURL+"upload/graduateInfo",
      title: '',
      tableData: [
      ],
      loading:false,
      DialogVisible:false,
      // tableData: [],
      currentPage: 1,
      pageSize: 6,
      year:'',
      totalItems: 0,// 你的数据总条数,
      years:[],

    }
  },
  mounted()
  {
    // // this.handleSearch();
    this.loadTableData()
    this.loadYears()
  },
  methods:{
    loadYears(){
      api.get('/statistics/graduateyear').then(res=>{
        this.years = res;
      })
    },
    edit(row){

      // await ElMessage.warning('正在修改状态，请稍后  '+row.state);
      api.put('/employmentReportState', null, {
        params: {
          reportID: row.reportID,
          status: row.state==='1'?'1':'0',
        }
      }).then(res => {
        this.$message({
          type: 'success',
          message: '修改成功!'

        })
        // this.loadTableData()
      }).then(r=>{
       setTimeout(()=>{
         this.loadTableData()
       },500)

      })

    },
    loadTableData(){
      this.loading=true

      setTimeout(() => {
        api.get('/employmentReports').then(res => {
          this.tableData = res
          this.totalItems = res.length
        })
        this.loading = false
      }, 500)
      // this.loading = false
    },

    handleSearch(){
      this.loadTableData();
    },
    handlePreview(url){
      // window.open('http://localhost:3000/preview/'+fileName);
      this.$router.push('/graduate/preview/'+url)
    },
    handleCurrentChange(val) {
      this.loading=true
      this.currentPage = val;
    },
    handleGenerate(){
      if(this.year==''){
        this.$message({
          type: 'warning',
          message: '请先选择年份!'
        })
        return;
      }
      this.$confirm('此操作生成'+this.year+'年度就业质量报告, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        api.get('/employmentReport', {
          params: {
            year: this.year,
            userid:18,
          }
        }).then(res => {
          this.$message({
            type: 'success',
            message: '生成成功!'
          })
          api.post('http://127.0.0.1:7000/hdfs/report',
            res).then(res=>{
              if(res.data.code==200){
                this.$message({
                  type: 'success',
                  message: '报告上传成功!'
                })
              }
          })
        }).then(r=>{
          this.loadTableData();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消生成'
        });
      });
    },
    uploadFile(files){
        let file = files.file
      // 使用FormData传参数和文件
        var form = new FormData();
      // 添加键值对
        form.append("graduateInfo", file);
      // form.append("date", this.urlId);
      // 传给后台FormData， 此时form = {date: xx, file: xxx(FormData)}
       api.post('/upload/graduateInfo', form).then(res => {
        this.$message({
          type: 'success',
          message: '上传成功!'
        })
        // this.loadTableData();
    })
    },
    downloadTemplate(){
      window.open('http://localhost:3000/graduate/template')
    },
      submitUpload() {
          uploadRef.value!.submit();
        this.loadTableData();
      },
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
        数据管理
      </template>
      <template #content>

      </template>
    </PageHeader>
    <PageMain>
      <template #title>
        <el-form :inline="true"  style="width: 1500px;">

<!--          <el-form-item label="问卷标题"  >-->
<!--            <el-input v-model="title" placeholder="问卷标题，模糊查询"/>-->
          <el-row>
            <el-col span="4">
              <el-form-item>
                <el-button type="primary" @click="DialogVisible=true">导入数据</el-button>
              </el-form-item>
            </el-col>
            <el-col span="8">
         <div></div>
            </el-col>
            <el-col span="12">
              <el-form-item>
                <!--            <el-button type="primary" @click="handleSearch">查询</el-button>-->
                <el-select v-model="year" placeholder="请选择年份">
                  <el-option
                      v-for="item in years"
                      :key="item"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button @click="handleGenerate">生成报告</el-button>
              </el-form-item>
            </el-col>
          </el-row>

<!--          </el-form-item>-->


        </el-form>
        <!--      <el-button type="primary" @click="addQuestionnaire">添加问卷</el-button>-->
      </template>
      <template #default>
        <el-table :data="currentPageData" style="width: 100%" v-loading="loading">
          <el-table-column type="index" label="序号" width="80"></el-table-column>
          <el-table-column
              prop="title"
              label="报告标题"
              width="180">
          </el-table-column>
          <el-table-column
              prop="time"
              label="创建时间"
              width="300"
          >
          </el-table-column>
          <el-table-column
              prop="status"
              label="是否发布"
              width="180">
            <template #default="scope">
              <el-switch @change="edit(scope.row)"
                  v-model="scope.row.state"
                  active-color="#409eff"
                  inactive-color="gray"
                  active-value="1"
                  inactive-value="0"
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
              <el-button link type="primary"  @click="handlePreview(scope.row.url)">
                <SvgIcon name="ion:eye-outline"/>&nbsp;
                查看</el-button>
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
    <HDialog  ref="editDialog" v-model="DialogVisible"  title="文件上传">
      <el-upload
          ref="uploadRef"
          class="upload-demo"
          action=""
          drag
          :limit="1"
          :auto-upload="false"
          :http-request="uploadFile"
          accept=".xls,.xlsx"
      >
      <SvgIcon name="ep:upload-filled" size="50px"></SvgIcon>
        <div class="el-upload__text">
         拖动文件或<em>点击</em>上传
        </div>
        <template #tip>
          <div class="el-upload__tip" style="font-size: 14px">
            请先下载模板，按照格式填写数据，命名为20xx年毕业生数据后上传

          </div>
        </template>
      </el-upload>
      <el-divider direction="horizontal"></el-divider>
      <div slot="footer" class="dialog-footer" >
        <el-row>
          <el-col :span="4">
            <el-button type="danger" link @click="DialogVisible = false" style="font-size: 16px">
              <SvgIcon name="material-symbols:download-sharp"/>
                <a href="https://file.ziling.site/down.php/1960a30500f75daac3181663c9c7a7f7.xlsx" download> 下载模板</a>
             </el-button>
          </el-col>
          <el-col :span="12">
<!--            <el-button type="primary" @click="DialogVisible = false">取 消</el-button>-->
            <span></span>
          </el-col>
          <el-col :span="4">
            <el-button  @click="DialogVisible = false">取 消</el-button>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" @click="submitUpload">确 定</el-button>
          </el-col>
        </el-row>
      </div>
    </HDialog>
  </div>
</template>

<style scoped lang="scss">
a{
  text-decoration:none;
  color:#F56C6C;
}
a :hover{
  text-decoration:none;
  color: #fab6b6;
}
</style>
