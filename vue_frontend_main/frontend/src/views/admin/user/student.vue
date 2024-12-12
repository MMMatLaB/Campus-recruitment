<script setup lang="ts">
// import axios from 'axios'
// import useUserStore from "@/store/modules/user.ts";
import {functions} from "lodash-es";
import api from "@/api";
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
        major: '',
      },
      addUserForm: {
        stuId: '',
        name: '',
        majorId: '',
        tel: '',
        status: '',
      },
      editUser:{
        stuId: '',
        name: '',
        major: '',
        tel: '',
        status: '',
      },
      majors:[{
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
    this.LoadStudents()
    this.LoadMajors()
    // this.LoadMajors()
  },
  methods: {
    LoadMajors(){
      // this.loading=true
      api.get('/majors',{
        params:{
          major: '',
        }}
      ).then((res)=>{
        this.majors=res as any
      }).catch((err)=>{
        ElMessage.error(err)
      })
      // this.loading=false
    },
    handleSearch() {
      this.LoadStudents()

    },
    LoadStudents(){
        this.loading=true
        api.get('/studentUsers',{
          params:{
            userName: this.searchParam.userName,
            realName: this.searchParam.realName,
            major: this.searchParam.major,
          }
        }).then((res)=>{
          this.tableData = res as any
          this.totalItems = this.tableData.length
          // ElMessage.success('查询成功')
          // ElMessage.success(res.toString())
        }).catch(error => {
          // // 请求失败处理
          // ElMessage.success(this.searchParam.userName)
          // ElMessage.warning("查询失败")
        });
      this.loading=false
    },
    handleEdit(row:any ){
      this.editUser = row
      this.EditUserDialog = true
    },
    SubmitEditUser(){
      // ElMessage.success("编辑" + this.editUser.name+
      //   "学号"+this.editUser.stuId+
      //   "专业"+this.editUser.major+
      //   "电话"+this.editUser.tel+
      //   "状态"+this.editUser.status
      // )
      api.put('/studentUser/',
          {
            major:JSON.parse(JSON.stringify(this.editUser.major)),
            name: JSON.parse(JSON.stringify(this.editUser.name)),
            status: JSON.parse(JSON.stringify(this.editUser.status)),
            tel: JSON.parse(JSON.stringify(this.editUser.tel)),
            stuId: JSON.parse(JSON.stringify(this.editUser.stuId))
          }).then((res)=>{
          this.EditUserDialog=false
          this.LoadStudents()
        // ElMessage.success("响应："+res.code)
      }).catch(error => {
        // ElMessage.error("修改失败")
        // // ElMessage.error(error.code.toString())
        // ElMessage.error(this.editUser.major+"\n"+
        //     this.editUser.name+"\n"+
        //     this.editUser.status+"\n"+
        //     this.editUser.tel+"\n"+
        //     this.editUser.stuId)
      });

    },
    SubmitNewUser(){
      api.post('/studentUser',
        {
          major_mid:this.addUserForm.majorId,
          realName: this.addUserForm.name,
          status: this.addUserForm.status,
          telephone: this.addUserForm.tel,
          userName: this.addUserForm.stuId,
          email:'',
          gender:'未知',
          cid:-1,
          password:'88888888',
          userID:'',
          created_time: new Date().toString(),
        }).then((res)=>{
          // ElMessage.success()
        // ElMessage.success("响应："+res.code)
        if(res.code==200){
          ElMessage.success("新增成功")
        }else {
          ElMessage.error("新增失败")
        }
      }).catch(error => {
        ElMessage.error("新增失败")
        // ElMessage.error(error.code.toString())
      });
        this.newUserDialog=false
      this.LoadStudents()
    },
    handleDelete(row:any){
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        api.delete('/studentUser', {
              params: {
                userName: row.stuId
              }
            }
        ).then((res:any)=>{
          // ElMessage.success("删除成功")
          ElMessage.success(res.code.toString())
          this.LoadStudents()
        }).catch(error => {
          ElMessage.error(error.code.toString())
          // ElMessage.error("删除"+row.stuId+"失败")
          // ElMessage.error("删除失败")
        });
        this.LoadStudents()
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
          this.addUserForm.majorId=''
          this.addUserForm.tel=''
          this.addUserForm.status=''

      },
      exitEditUser(){
        this.EditUserDialog=false
        this.editUser.stuId=''
        this.editUser.name=''
        this.editUser.major=''
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
    <PageHeader title="学生用户管理" content="" />
    <PageMain>
      <template #title>
        <el-form :inline="true" :model="searchParam" style="width: 1500px;">
          <el-form-item label="学号" prop="id" >
            <el-input v-model="searchParam.userName" placeholder="学号，模糊查询"/>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="searchParam.realName" placeholder="姓名，模糊查询"/>
          </el-form-item>
          <el-form-item label="专业" prop="major">

            <el-input v-model="searchParam.major" placeholder="专业，模糊查询"/>
          </el-form-item>
          <el-form-item>
            <el-button  @click="handleSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="clickNewStudent">
                <SvgIcon name="gridicons:add-outline"/>&nbsp;
                新增学生用户</el-button>
          </el-form-item>

        </el-form>

      </template>
      <!--    <el-button type="primary" @click="handleEdit(-1, {})">导出学生用户</el-button> -->
      <el-table :data="currentPageData" border style="width: 100%"  v-loading="loading">
        <el-table-column type="index" label="序号" width="80" align="center"/>
        <el-table-column prop="stuId" label="学号" align="center"/>
        <el-table-column prop="name" label="姓名" align="center"/>
        <el-table-column prop="major" label="专业" align="center"/>
        <el-table-column prop="tel" label="电话" align="center"/>
        <el-table-column label="状态" width="80" align="center">
          <template #default="scope">
            <el-tag :type="scope.row.status === 0 ? 'danger' : 'success'">
              {{ scope.row.status === 0 ? '禁用' : '启用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180">
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
    <HDialog v-model="newUserDialog" title="新增学生用户">
      <template #default>
        <div style="width: 50%">
           <el-form v-model="addUserForm" >
            <el-form-item label="学号" prop="stuId">
              <el-input v-model="addUserForm.stuId" />
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="addUserForm.name" />
            </el-form-item>
            <el-form-item label="专业" prop="major">
              <el-select v-model="addUserForm.majorId" placeholder="请选择专业"  >
                <el-option v-for="major in majors" :key="major" :label="major.majorName" :value="major.mid">
                  {{ major.majorName }}
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="tel">
              <el-input v-model="addUserForm.tel" />
            </el-form-item>
              <el-form-item
                      prop="status"
                      label="是否启用"
                      width="180">
<!--                  <template #default="scope">-->
                      <el-switch
                              v-model="addUserForm.status"
                              active-color="#409eff"
                              inactive-color="gray"
                              active-value="1"
                              inactive-value="0"
                              inline-prompt
                              active-text="是"
                              inactive-text="否">
                      </el-switch>
<!--                  </template>-->
              </el-form-item>
<!--          <el-form-item>-->
<!--              {{addUserForm.status}}-->
<!--          </el-form-item>-->
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


    <HDialog v-model="EditUserDialog" title="修改学生用户">
      <template #default>
        <div style="width: 50%">
          <el-form v-model="editUser">
            <el-form-item label="学号" prop="stuId">
              <el-input v-model="editUser.stuId" disabled/>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="editUser.name" />
            </el-form-item>
            <el-form-item label="专业" prop="major">
<!--              <el-select v-model="editUser.majorId" placeholder="请选择专业" clearable autocomplete="true">-->
<!--                <el-option v-for="major in majors" :key="major" :label="major.majorName" :value="major.mid" >-->
<!--                  {{ major.majorName }}-->
<!--                </el-option>-->
<!--              </el-select>-->
              <el-select v-model="editUser.major" placeholder="请选择">
                <el-option
                    v-for="major in majors"
                    :key="major.mid"
                    :label="major.majorName"
                    :value="major.majorName"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="tel">
              <el-input v-model="editUser.tel" />
            </el-form-item>
              <el-form-item
                      prop="status"
                      label="是否启用"
                      width="180">
                  <!--                  <template #default="scope">-->
                  <el-switch
                          v-model="editUser.status"
                          active-color="#409eff"
                          inactive-color="gray"
                          :active-value="1"
                          :inactive-value="0"
                          inline-prompt
                          active-text="是"
                          inactive-text="否">
                  </el-switch>
                  <!--                  </template>-->
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
