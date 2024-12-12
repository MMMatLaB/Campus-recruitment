<script setup lang="ts">
// import axios from 'axios'
// import useUserStore from "@/store/modules/user.ts";
// import {functions} from "lodash-es";
// import api from "@/api";
</script>

<script lang="ts">
import { ElMessage } from 'element-plus'
import api from "@/api";
export default {
  data() {
    return{
      loading:true,
      students:[],
      EditUserDialog:false,
      newUserDialog:false,
      searchParam:{
        userName: '',
        realName: '',
        company: '',
      },
      addUserForm: {
        stuId: '',
        name: '',
        cId: '',
        tel: '',
        status: '',
      },
      editUser:{
        hrid: '',
        name: '',
        company: '',
        tel: '',
        status: '',
      },
      companys:[{
        mid: null,
        majorName: null,
      }],
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      totalItems: 0
    }
  },
  mounted() {
    this.LoadTableData()
    this.LoadCompanys()
    // this.LoadMajors()
  },
  methods: {
    LoadCompanys(){
      // this.loading=true
      api.get('/companys'
      ).then((res)=>{
        this.companys=res as any
      }).catch((err)=>{
        ElMessage.error(err)
      })
      // this.loading=false
    },
    handleSearch() {
      this.LoadTableData()

    },
    LoadTableData(){
      this.loading=true


      api.get('/companyUsers',{
        params:{
          userName: this.searchParam.userName,
          realName: this.searchParam.realName,
          company: this.searchParam.company,
        }
      }).then((res)=>{
        this.tableData = res as any
        this.totalItems = this.tableData.length
        // ElMessage.success('查询成功')
        // ElMessage.success(res.toString())
        setTimeout(()=>{
          this.loading=false
        },500);
      }).catch(error => {
        // // 请求失败处理
        // ElMessage.success(this.searchParam.userName)
        // ElMessage.warning("查询失败")
      });

    },
    handleEdit(row:any ){
      this.editUser = row
      this.EditUserDialog = true
    },
    SubmitEditUser(){

      api.put('/companyUser',
          {
            company:this.editUser.company,
            name: this.editUser.name,
            status: this.editUser.status,
            tel: this.editUser.tel,
            hrid: this.editUser.hrid
          }).then((res)=>{
        this.LoadTableData()
        this.EditUserDialog=false
      }).catch(error => {
        ElMessage.error("修改失败"+this.editUser.hrid)
      });


    },
    SubmitNewUser(){
      api.post('/companyUser',
          {
            major_mid:-1,
            realName: this.addUserForm.name,
            status: this.addUserForm.status,
            telephone: this.addUserForm.tel,
            userName: this.addUserForm.stuId,
            email:'',
            gender:'未知',
            cid:this.addUserForm.cId,
            password:'88888888',
            userID:'',
            created_time: new Date().toString(),
          }).then((res)=>{
        // ElMessage.success()
        // ElMessage.success("响应："+res.code)
        this.newUserDialog=false
        this.LoadTableData()
      }).catch(error => {
        ElMessage.error("新增失败")
        // ElMessage.error(error.code.toString())
      });

    },
    handleDelete(row:any){
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        api.delete('/companyUser', {
              params: {
                userName: row.hrid
              }
            }
        ).then((res:any)=>{
          // ElMessage.success("删除成功")
          ElMessage.success(res.code.toString())
          this.LoadTableData()
        }).catch(error => {
          ElMessage.error(error.code.toString())
          // ElMessage.error("删除"+row.stuId+"失败")
          // ElMessage.error("删除失败")
        });
        this.LoadTableData()
      });


    },
    clickNewStudent()
    {
      this.newUserDialog=true

    },
    exitNewUser(){
      this.newUserDialog=false
      this.addUserForm.stuId=''
      this.addUserForm.name=''
      this.addUserForm.cId=''
      this.addUserForm.tel=''
      this.addUserForm.status=''

    },
    exitEditUser(){
      this.EditUserDialog=false
      this.editUser.hrid=''
      this.editUser.name=''
      this.editUser.company=''
      this.editUser.tel=''
      this.editUser.status=''
    }, handleCurrentChange(val) {
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
    <PageHeader title="企业用户管理" content="" />
    <PageMain>
      <template #title>
        <el-form :inline="true" :model="searchParam" style="width: 1500px;">
          <el-form-item label="工号" prop="id" >
            <el-input v-model="searchParam.userName" placeholder="工号，模糊查询"/>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="searchParam.realName" placeholder="姓名，模糊查询"/>
          </el-form-item>
          <el-form-item label="公司" prop="companyName">
            <el-input v-model="searchParam.company" placeholder="公司，模糊查询"/>
          </el-form-item>
          <el-form-item>
            <el-button  @click="handleSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="clickNewStudent">
                <SvgIcon name="gridicons:add-outline"/>&nbsp;新增企业用户</el-button>
          </el-form-item>

        </el-form>

        <!--        <el-divider direction="horizontal"></el-divider>-->


        <!--        <el-divider direction="horizontal"></el-divider>-->

        <!--      <el-button type="primary" @click="handleEdit(-1, {})">批量导入</el-button> -->
      </template>
      <!--    <el-button type="primary" @click="handleEdit(-1, {})">导出学生用户</el-button> -->
      <el-table :data="currentPageData" border style="width: 100%"  v-loading="loading">
        <el-table-column type="index" label="序号" width="80" align="center"/>
        <el-table-column prop="hrid" label="工号"align="center" />
        <el-table-column prop="name" label="姓名" align="center"/>
        <el-table-column prop="company" label="公司" align="center"/>
        <el-table-column prop="tel" label="电话"align="center" />
        <el-table-column label="状态" width="80"align="center">
          <template #default="scope">
            <el-tag :type="scope.row.status === 0 ? 'danger' : 'success'">
              {{ scope.row.status === 0 ? '禁用' : '启用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.row)" >
              编辑
            </el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope.row)">
              删除
            </el-button>
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
    </PageMain>

    <!--    新增用户对话窗    -->
    <HDialog v-model="newUserDialog" title="新增企业用户">
      <template #default>
        <div style="width: 50%">
          <el-form v-model="addUserForm" >
            <el-form-item label="工号" prop="hrId">
              <el-input v-model="addUserForm.stuId" />
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="addUserForm.name" />
            </el-form-item>
            <el-form-item label="公司" prop="companyName">
              <el-select v-model="addUserForm.cId" placeholder="请选择公司" clearable autocomplete="true">
                <el-option v-for="company in companys" :key="company" :label="company.companyName" :value="company.cid" >
                  {{ company.companyName }}
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="tel">
              <el-input v-model="addUserForm.tel" />
            </el-form-item>
            <el-form-item prop="status" label="状态">
              <!--              <el-radio-group v-model="addUserForm.status">-->
              <!--                <el-radio label="0">禁用</el-radio>-->
              <!--                <el-radio label="1">启用</el-radio>-->
              <!--              </el-radio-group>-->
              <el-switch
                  v-model="addUserForm.status"
                  active-text="启用"
                  active-value="1"
                  inactive-value="0"
                  inactive-text="禁用"
              ></el-switch>
            </el-form-item>
            <el-form-item>
              <el-text type="info">初始密码为:&nbsp;88888888</el-text>
            </el-form-item>
          </el-form>
        </div>
      </template>
      <template #footer>
        <el-button @click="exitNewUser">取消</el-button>
        <el-button @click="SubmitNewUser" type="primary" >
          确定
        </el-button>
      </template>
    </HDialog>


    <HDialog v-model="EditUserDialog" title="修改企业用户">
      <template #default>
        <div style="width: 50%">
          <el-form v-model="editUser">
            <el-form-item label="工号" prop="hrId">
              <el-input v-model="editUser.hrid" disabled/>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="editUser.name" />
            </el-form-item>
            <el-form-item label="公司" prop="companyName">
              <el-select v-model="editUser.company" placeholder="请选择公司" clearable autocomplete="true">
                <el-option v-for="company in companys" :key="company" :label="company.companyName" :value="company.companyName" >
                  {{ company.companyName }}
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="tel">
              <el-input v-model="editUser.tel" />
            </el-form-item>
            <el-form-item prop="status" label="状态">
              <el-switch
                  v-model="editUser.status"
                  active-text="启用"
                  :active-value="1"
                  :inactive-value="0"
                  inactive-text="禁用"
              ></el-switch>
            </el-form-item>
          </el-form>
        </div>
      </template>
      <template #footer>
        <el-button @click="exitEditUser">取消</el-button>
        <el-button type="primary" @click="SubmitEditUser">
          确定
        </el-button>
      </template>
    </HDialog>
  </div>
</template>

<style scoped lang="scss">
</style>
