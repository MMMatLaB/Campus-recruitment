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
      domains:[
        { value: "互联网", label: "互联网" },
        { value: "电子商务", label: "电子商务" },
        { value: "金融", label: "金融" },
        { value: "企业服务", label: "企业服务" },
        { value: "教育", label: "教育" },
        { value: "文化娱乐", label: "文化娱乐" },
        { value: "游戏", label: "游戏" },
        { value: "硬件", label: "硬件" },
        { value: "社交网络", label: "社交网络" },
        { value: "旅游", label: "旅游" },
        { value: "医疗健康", label: "医疗健康" },
        { value: "生活服务", label: "生活服务" },
        { value: "信息安全", label: "信息安全" },
        { value: "数据服务", label: "数据服务" },
        { value: "广告营销", label: "广告营销" },
        { value: "分类信息", label: "分类信息" },
        { value: "招聘", label: "招聘" },
        { value: "其他", label: "其他" }
      ],
      capacitys:[
        {
          value: "0-20人",
          label: "0-20人"
        },
        {
          value: "20-99人",
          label: "20-99人"
        },
        {
          value: "100-499人",
          label: "100-499人"
        },
        {
          value: "500-999人",
          label: "500-999人"
        },
        {
          value: "1000-9999人",
          label: "1000-9999人"
        },
        {
          value: "10000人以上",
          label: "10000人以上"
        }
      ],
      loading:true,
      companys:[],
      EditCompanyDialog:false,
      newCompanyDialog:false,
      searchParam:{
        idcorporation:'',
        capacity: '',
        domain: '',
        corpName: '',
        breifInfo:'',
      },
      addCompanyForm: {
        breifInfo: '',
        capacity: '',
        corpName: '',
        domainn: '',
        idcorporation: '',
      },
      editCompany:{
        breifInfo: '',
        capacity: '',
        corpName: '',
        domainn: '',
        idcorporation: '',
      },
      // majors:[{
      //   mid: null,
      //   majorName: null,
      // }],
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      totalItems: 0
    }
  },
  mounted() {
    this.LoadCompany()
  },
  methods: {
    handleSearch() {
      this.LoadCompany()

    },
    LoadCompany(){
      this.loading=true
      api.get('/corps',{
        params:{
          capacity: this.searchParam.capacity,
          domainn: this.searchParam.domain,
          corpName:this.searchParam.corpName,
          breifInfo: this.searchParam.breifInfo,
        }
      }).then((res)=>{
        this.tableData = res as any
        this.totalItems = this.tableData.length
        ElMessage.success('查询成功')
        this.loading=false
        // ElMessage.success(res.toString())
      }).catch(error => {
        // // 请求失败处理
        // ElMessage.success(this.searchParam.userName)
        ElMessage.warning("查询失败")
      });

    },
    handleEdit(row:any ){
      this.editCompany = row
      this.EditCompanyDialog = true
    },
    SubmiteditCompany(row){
      api.put('/corp/'+row.idcorporation,
        {
          breifInfo:JSON.parse(JSON.stringify(this.editCompany.breifInfo)),
          capacity: JSON.parse(JSON.stringify(this.editCompany.capacity)),
          corpName: JSON.parse(JSON.stringify(this.editCompany.corpName)),
          domainn: JSON.parse(JSON.stringify(this.editCompany.domainn)),
          idcorporation: JSON.parse(JSON.stringify(this.editCompany.idcorporation))
        }).then((res)=>{
        this.EditCompanyDialog=false
        this.LoadCompany()
        // ElMessage.success("响应："+res.code)
      }).catch(error => {

      });

    },
    SubmitNewCompany(){
      api.post('/corp',
        {
          breifInfo:this.addCompanyForm.breifInfo,
          capacity: this.addCompanyForm.capacity,
          corpName: this.addCompanyForm.corpName,
          idcorporation: 0,
          domainn: this.addCompanyForm.domainn,
        }).then((res)=>{
        // ElMessage.success()
        // ElMessage.success("响应："+res.code)
        if(res.code==200){
          ElMessage.success("新增成功")
          this.LoadCompany()
        }else {
          ElMessage.error("新增失败")
        }
      }).catch(error => {
        ElMessage.error("新增失败")
        // ElMessage.error(error.code.toString())
      });
      this.newCompanyDialog=false
      // this.LoadCompany()
    },
    handleDelete(row){
      this.$confirm('此操作将永久删除该公司, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        api.delete('/corp/'+row.idcorporation, {
            // params: {
            //   userName: row.stuId
            // }
          }
        ).then((res:any)=>{
          // ElMessage.success("删除成功")
          ElMessage.success(res.code.toString())
          this.LoadCompany()
        }).catch(error => {
          ElMessage.error(error.code.toString())
          // ElMessage.error("删除"+row.stuId+"失败")
          // ElMessage.error("删除失败")
        });
        // this.LoadCompany()
      });

    },
    clickNewCompany()
    {
      this.newCompanyDialog=true

    },
    exitNewUser(){
      this.newCompanyDialog=false
      this.addCompanyForm.breifInfo=''
      this.addCompanyForm.capacity=''
      this.addCompanyForm.corpName=''
      this.addCompanyForm.idcorporation=''
      this.addCompanyForm.domainn=''

    },
    exiteditCompany(){
      this.EditCompanyDialog=false
      this.editCompany.breifInfo=''
      this.editCompany.capacity=''
      this.editCompany.corpName=''
      this.editCompany.domainn=''
      this.editCompany.idcorporation=''
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
    <PageHeader title="公司管理" content="" />
    <PageMain>
      <template #title>
        <el-form :inline="true" :model="searchParam" style="width: 1500px;">
          <el-form-item>
            <el-button type="primary" @click="clickNewCompany">
              <SvgIcon name="gridicons:add-outline"/>&nbsp;
              新增公司</el-button>
          </el-form-item>

        </el-form>

      </template>
      <!--    <el-button type="primary" @click="handleEdit(-1, {})">导出学生用户</el-button> -->
      <el-table :data="currentPageData" border style="width: 100%"  v-loading="loading">
        <el-table-column type="index" label="序号" width="80" align="center"/>
        <el-table-column prop="corpName" label="公司名称" align="center"/>
        <el-table-column prop="domainn" label="公司领域" align="center"/>
        <el-table-column prop="capacity" label="公司规模" align="center"/>
        <el-table-column label="操作" width="180" align="center">
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
    <HDialog v-model="newCompanyDialog" title="新增公司">
      <template #default>
        <div style="width: 50%">
          <el-form v-model="addCompanyForm" >
            <el-form-item label="公司名称" prop="corpName">
              <el-input v-model="addCompanyForm.corpName" />
            </el-form-item>
            <el-form-item label="公司规模" prop="capacity">
<!--              <el-input v-model="addCompanyForm.capacity" />-->
              <el-select v-model="addCompanyForm.capacity" placeholder="请选择">
                <el-option
                    v-for="item in capacitys"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="公司领域" prop="domain">
              <!--              <el-input v-model="editCompany.domainn" />-->
              <el-select v-model="addCompanyForm.domainn" placeholder="请选择">
                <el-option
                    v-for="item in domains"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>
      </template>
      <template #footer>
        <el-button @click="exitNewUser">取消</el-button>
        <el-button @click="SubmitNewCompany" type="primary" >
          确定
        </el-button>
      </template>
    </HDialog>


    <HDialog v-model="EditCompanyDialog" title="修改公司">
      <template #default>
        <div style="width: 50%">
          <el-form v-model="editCompany">
            <el-form-item label="公司名称" prop="corpName">
              <el-input v-model="editCompany.corpName" />
            </el-form-item>
            <el-form-item label="公司规模" prop="capacity">
              <el-select v-model="editCompany.capacity" placeholder="请选择">
                <el-option
                    v-for="item in capacitys"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="公司领域" prop="domain">
<!--              <el-input v-model="editCompany.domainn" />-->
            <el-select v-model="editCompany.domainn" placeholder="请选择">
              <el-option
                v-for="item in domains"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
            </el-form-item>
<!--            <el-form-item label="公司简介" prop="breifInfo">-->
<!--              <el-input v-model="editCompany.breifInfo" />-->
<!--            </el-form-item>-->
<!--            <el-form-item-->
<!--              prop="status"-->
<!--              label="是否启用"-->
<!--              width="180">-->
<!--              &lt;!&ndash;                  <template #default="scope">&ndash;&gt;-->
<!--              <el-switch-->
<!--                v-model="editCompany.status"-->
<!--                active-color="#409eff"-->
<!--                inactive-color="gray"-->
<!--                :active-value="1"-->
<!--                :inactive-value="0"-->
<!--                inline-prompt-->
<!--                active-text="是"-->
<!--                inactive-text="否">-->
<!--              </el-switch>-->
<!--              &lt;!&ndash;                  </template>&ndash;&gt;-->
<!--            </el-form-item>-->
          </el-form>
        </div>
      </template>
      <template #footer>
        <el-button @click="exiteditCompany">取消</el-button>
        <el-button type="primary" @click="SubmiteditCompany">
          确定
        </el-button>
      </template>
    </HDialog>
  </div>
</template>

<style scoped lang="scss">
</style>
