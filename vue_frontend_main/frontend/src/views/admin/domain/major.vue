<script lang="ts">
import {ElMessage} from "element-plus";
import api from "@/api";
import { FormInstance, FormRules } from 'element-plus'

const ruleFormRef = ref<FormInstance>()

export default {
  data() {
    return {
      majors: [],
      currentPage: 1,
      pageSize: 10,
      totalItems: 0,
      tableData: [],
      searchParams:{
        major: '',
      },
      loading:false,
      dialogVisible: false,
      newMajor: {
        name: '',
      },
      // search
      rules:{
        name: [
          { required: true, message: '专业名不能为空', trigger: 'blur' },
        ],
      }
    }
  },

  mounted() {
    this.LoadMajors()
  },
  methods: {
    LoadMajors(){
      this.loading=true
      api.get('/majors',{
        params:{
          major: this.searchParams.major,
        }}
      ).then((res)=>{
        this.tableData=res as any
        this.totalItems=res.length
      }).catch((err)=>{
        ElMessage.error(err)
      })
      this.loading=false
    },

    deleteMajor(major) {
      // console.log(`/major/${major.mid}`)
      this.$confirm('此操作将永久删除该专业, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        api.delete('/major/' + major.mid,
        ).then((res) => {
          ElMessage.success('删除成功')
          this.LoadMajors()
        }).catch((err) => {
          ElMessage.error(err)
        })
      })

    },
    closeDialog() {
      this.dialogVisible = false
      // this.$refs.newMajorForm.resetFields()
    },
    addMajor() {
      this.$refs.ruleFormRef.validate((valid) => {
        if(valid) {
          this.loading = true
          api.post('/major', {
            majorName: this.newMajor.name,
          }).then((res) => {
            ElMessage.success('添加成功')
            this.LoadMajors()
            this.closeDialog()
          }).catch((err) => {
            ElMessage.error(err)
          })
        }
      })
    },
    handleCurrentChange(val) {
      this.loading=true
      this.currentPage = val;
    }
  },
  computed: {
    currentPageData() {
      setTimeout(()=>{
        this.loading=false
      },500);
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.tableData.slice(start, end);
    }
  },
}
</script>

<template>
  <div>
    <PageHeader>
      <template #title>
        专业管理
      </template>
    </PageHeader>
    <PageMain>
      <template #title>
        <el-form :inline="true" :model="searchParams" style="width: 1500px;">
          <el-form-item label="专业" prop="major">
            <el-input v-model="searchParams.major" placeholder="专业，模糊查询"/>
          </el-form-item>
          <el-form-item>
            <el-button  @click="LoadMajors">查询</el-button>
          </el-form-item>
          <el-form-item>
            <!--           <el-table-column>-->
            <el-button type="primary" @click="dialogVisible=true">
              <SvgIcon name="gridicons:add-outline"/>&nbsp;
              新增专业
            </el-button>
            <!--           </el-table-column>-->
          </el-form-item>
        </el-form>

      </template>

      <el-table :data="currentPageData" style="width: 100%" v-loading="loading" border>
        <el-table-column type="index" label="序号" width="180" align="center"></el-table-column>
        <el-table-column prop="majorName" label="名称" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template #default="scope" style="align-content: center">
            <!--            <el-button type="primary" size="small" @click="editMajor(scope.row)">编辑</el-button>-->
            <el-button type="danger" size="small" @click="deleteMajor(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-divider direction="horizontal" border-style="hidden"></el-divider>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 50]"
        :page-size="pageSize"
        :total="totalItems"
      ></el-pagination>

      <HDialog v-model="dialogVisible" title="新增专业">
        <template #default>
          <div style="width: 50%">
            <ElForm ref="ruleFormRef" :model="newMajor" :rules="rules">
              <ElFormItem label="名称" prop="name">
                <ElInput v-model="newMajor.name" />
              </ElFormItem>
            </ElForm>
          </div>
        </template>
        <template #footer>
          <el-button @click="closeDialog">取消</el-button>
          <el-button type="primary" @click="addMajor">
            确定
          </el-button>
        </template>
      </HDialog>
    </PageMain>

  </div>
</template>

<style scoped lang="scss">

</style>
