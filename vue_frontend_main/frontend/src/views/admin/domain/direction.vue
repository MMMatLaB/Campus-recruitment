<script lang="ts">
import {ElMessage} from "element-plus";
import api from "@/api";
import { FormInstance, FormRules } from 'element-plus'

const ruleFormRef = ref<FormInstance>()

export default {
  data() {
    return {
      loading:false,
      currentPage: 1,
      pageSize: 10,
      totalItems: 0,
      tableData: [],
      majors: [],
      searchParams:{
        major:'',
      },
      // directions: [
      //   {id: 1, name: '专业1'},
      //   {id: 2, name: '专业2'},
      //   // 初始专业数据
      // ],
      dialogVisible: false,
      newDirection: {
        name: '',
      },
      rules: {
        direction: [
          {required: true, message: '方向名不能为空', trigger: 'blur'},
        ],
      },
    }
  },
  mounted() {
    this.loadMajor()
    this.loadDirection()
  },
  methods: {
    handleCurrentChange(val) {
      this.loading=true
      this.currentPage = val;
    },
    loadMajor(){
      api.get("/majors",{
        params:{
          major:''
        }
      }).then((res)=>{
        this.majors=res
       // console.log(this.majors)
      })
    },
    loadDirection(){
      // ElMessage.success(this.searchParams.major)
      // console.log(this.searchParams)
      api.get("/directions",{
        params:{
          direction:'',
          major:this.searchParams.major
        }
      }).then((res)=>{
        this.tableData = res
        this.totalItems = this.tableData.length
        //console.log(res)
        // ElMessage.success(this.searchParams.major)
      })
    },
    deleteDirection(direction) {
      this.$confirm('此操作将永久删除该方向, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        api.delete('/direction/' + direction.directid).then(() => {
          ElMessage.success("删除成功")
          this.loadDirection()
        }).catch((err) => {
          ElMessage.error(err)
        })
      })
    },
    closeDialog() {
      this.dialogVisible = false
      // this.$refs.newMajorForm.resetFields()
    },
    addDirection() {
      this.$refs.ruleFormRef.validate((valid) => {
        if(valid){
          this.loading = true
          api.post('/direction', {
            directname: this.newDirection.name,
          }).then((res) => {
            ElMessage.success('添加成功')
            this.loadDirection()
            this.closeDialog()
          }).catch((err) => {
            ElMessage.error(err)
          })
        }
      })
      //ElMessage.success(this.newDirection.name)
      //this.closeDialog()
    },
    clear() {
      this.searchParams.major = '';
      this.loadDirection()
    }

  },

computed: {
  currentPageData(){
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
        就业方向管理
      </template>
    </PageHeader>
    <PageMain>
      <template #title>

        <el-form :inline="true" :model="searchParams" style="width: 1500px;">
          <el-form-item label="专业" prop="majors">
            <el-select v-model="searchParams.major" placeholder="请选择专业"  >
              <el-option v-for="major in majors" :key="major" :label="major.majorName" :value="major.mid">
                {{ major.majorName }}
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button @click="loadDirection">
              查询
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button @click="clear">
              清除
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="dialogVisible=true">
              <SvgIcon name="gridicons:add-outline"/>
              &nbsp;
              新增就业方向
            </el-button>
          </el-form-item>
        </el-form>
      </template>
      <el-table :data="currentPageData" style="width: 100%" v-loading="loading" border>
        <el-table-column type="index" label="序号" width="180" align="center"></el-table-column>
        <el-table-column prop="directname" label="名称" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template #default="scope" style="align-content: center">
            <!--            <el-button type="primary" size="small" @click="editMajor(scope.row)">编辑</el-button>-->
            <el-button type="danger" size="small" @click="deleteDirection(scope.row)">删除</el-button>
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

      <HDialog v-model="dialogVisible" title="新增方向">
        <template #default>
          <div style="width: 50%">
            <el-form v-model="newDirection" ref="ruleFormRef" :rules="rules">
              <el-form-item label="方向名称" prop="direction">
                <el-input v-model="newDirection.name"/>
              </el-form-item>
            </el-form>
          </div>
        </template>
        <template #footer>
          <el-button @click="closeDialog">取消</el-button>
          <el-button type="primary" @click="addDirection">
            确定
          </el-button>
        </template>
      </HDialog>
    </PageMain>
  </div>
</template>

<style scoped lang="scss">

</style>
