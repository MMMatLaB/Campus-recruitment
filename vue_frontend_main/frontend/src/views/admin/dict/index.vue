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
      dic:[],
      EditUserDialog:false,
      newUserDialog:false,
      searchParam:{
        dictionaryid: 0,
        dictValue: '',
        dictType: '',
      },
      addDic: {
        dictType: '',
        dictValue: '',
        dictionaryid: 0
      },
      editDic:{
        dictType: 's1',
        dictValue: 's1',
        dictionaryid: 0
      },
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      totalItems: 0
    }
  },
  mounted() {
    this.LoadValue()
  },
  methods: {
    LoadValue(){
      this.loading=true
      api.get('/dicts',{
        params:{
          /*dictionaryid: this.searchParam.dictionaryid,
          dictValue: this.searchParam.dictValue,
          dictType: this.searchParam.dictType,*/
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
      this.editDic = row
      this.EditUserDialog = true
    },
    SubmitEditUser(row){
      // ElMessage.success("编辑" + this.editUser.name+
      //   "学号"+this.editUser.stuId+
      //   "专业"+this.editUser.major+
      //   "电话"+this.editUser.tel+
      //   "状态"+this.editUser.status
      // )
      api.post('/dict/put',
          {
            dictionaryid:parseInt(JSON.parse(JSON.stringify(this.editDic.dictionaryid))),
            dictValue: JSON.parse(JSON.stringify(this.editDic.dictValue)),
            dictType: JSON.parse(JSON.stringify(this.editDic.dictType))
          }).then((res)=>{
        this.EditUserDialog=false
        this.LoadValue()
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
      api.post('/dict',
          {
            dictType: this.addDic.dictType,
            dictValue: this.addDic.dictValue,
            dictionaryid: 0
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
      this.LoadValue()
    },
    handleDelete(row:any){
      /*'/dict/'+row.dictionaryid*/
      api.delete('/dict/'+row.dictionaryid, {
            params: {
              id: row.dictionaryid
            }
          }
      ).then((res:any)=>{
        // ElMessage.success("删除成功")
        ElMessage.success(res.code.toString())
        this.LoadValue()
      }).catch(error => {
        ElMessage.error(error.code.toString())
        // ElMessage.error("删除"+row.stuId+"失败")
        // ElMessage.error("删除失败")
      });
      this.LoadValue()
    },
    clickNewDic()
    {
      this.newUserDialog=true

    },
    exitNewDic(){
      this.newUserDialog=false
      this.addDic.dictionaryid=0
      this.addDic.dictValue=''
      this.addDic.dictType=''

    },
    exitEditDic(){
      this.EditUserDialog=false
      this.editDic.dictionaryid=0
      this.editDic.dictValue=''
      this.editDic.dictType=''
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
    <PageHeader title="字典管理" content="" />
    <PageMain>
      <template #title>
        <el-form-item>
          <el-button type="primary" @click="clickNewDic">
            <SvgIcon name="gridicons:add-outline"/>&nbsp;
            新增字典记录</el-button>
        </el-form-item>
      </template>
      <el-table :data="currentPageData" border style="width: 100%"  v-loading="loading">
        <el-table-column type="index" label="序号" width="80" align="center" />
        <el-table-column prop="dictType" label="数据类型"  align="center"/>
        <el-table-column prop="dictValue" label="数据值"  align="center"/>
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
    <HDialog v-model="newUserDialog" title="新增字典记录">
      <template #default>
        <div style="width: 50%">
          <el-form v-model="addDic" >
            <el-form-item label="记录号" prop="dictionaryid">
              <el-input v-model="addDic.dictionaryid" />
            </el-form-item>
            <el-form-item label="值" prop="dictValue">
              <el-input v-model="addDic.dictValue" />
            </el-form-item>
            <el-form-item label="类型" prop="dictType">
              <el-input v-model="addDic.dictType" />
            </el-form-item>
          </el-form>
        </div>
      </template>
      <template #footer>
        <el-button @click="exitNewDic">取消</el-button>
        <el-button @click="SubmitNewUser" type="primary" >
          确定
        </el-button>
      </template>
    </HDialog>

    <HDialog v-model="EditUserDialog" title="修改字典记录">
      <template #default>
        <div style="width: 50%">
          <el-form v-model="editDic">
            <el-form-item label="记录号" prop="dictionaryid">
              <el-input v-model="editDic.dictionaryid" disabled/>
            </el-form-item>
            <el-form-item label="值" prop="dictValue">
              <el-input v-model="editDic.dictValue" />
            </el-form-item>
            <el-form-item label="类型" prop="dictType">
              <el-input v-model="editDic.dictType" />
            </el-form-item>
          </el-form>
        </div>
      </template>
      <template #footer>
        <el-button @click="exitEditDic">取消</el-button>
        <el-button type="primary" @click="SubmitEditUser">
          确定
        </el-button>
      </template>
    </HDialog>
  </div>
</template>

<style scoped lang="scss">
</style>
