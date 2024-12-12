<script setup>
import JobDetailsDialog from "@/components/Window/JobDetailsDialog.vue";
import axios from "axios"
import Scroll from "@/views/examples/feature_example/scroll.vue";
import Index from "@/components/JobCard/index.vue";
// const currentDate = new Date();
// const formattedDate = format(currentDate, 'yyyy-MM-dd HH:mm:ss');
</script>
<script>
// import { format } from 'date-fns';
import {ElMessage} from "element-plus";
import api from "@/api";
import axios from "axios";
import apiUser from "../../api/modules/user"
export default {

  data() {
    return {
      workExperiences: [
        { value: "不限", label: "不限" },
        { value: "在校生", label: "在校生" },
        { value: "应届生", label: "应届生" },
        { value: "经验不限", label: "经验不限" },
        { value: "1年以内", label: "1年以内" },
        { value: "1-3年", label: "1-3年" },
        { value: "3-5年", label: "3-5年" },
        { value: "5-10年", label: "5-10年" },
        { value: "10年以上", label: "10年以上" }
      ],
      educationRequirements:[
        { value: "不限", label: "不限" },
        { value: "初中及以下", label: "初中及以下" },
        { value: "中专/中技", label: "中专/中技" },
        { value: "高中", label: "高中" },
        { value: "大专", label: "大专" },
        { value: "本科", label: "本科" },
        { value: "硕士", label: "硕士" },
        { value: "博士", label: "博士" }
      ],
      Preview: false,
      newCareer: false,
      editCareer: false,
      // tableData: [
      //   {id: 3, name: '岗位3', time: '2021-05-01', status: 1},
      //   {id: 2, name: '岗位2', time: '2021-05-01', status: 1},
      //   {id: 1, name: '岗位1', time: '2021-05-01', status: 0},
      // ],
      newCareerEntry: {
        name: "",
        workExperience: "",
        educationRequirement: "",
        JobLocation: [],
        upper:0,
        lower:0,
        status: 0
      },
      jobs:[],
      Curjob:{
        careerID:0,
        corporationidcorporation:1,
        careerName: "",
        workyear: "",
        educationlevel: "",
        workplace: "",
        salarymax:0,
        salarymin:0,
        status:0,
        careerInfo:"",
        creatdtime:"",
      },

      editCareerEntry: {careerID:0,
        corporationidcorporation:1,
        careerName: "",
        workyear: "",
        educationlevel: "",
        workplace: "",
        salarymax:0,
        salarymin:0,
        status:0,
        careerInfo:"",
        creatdtime:"",
      },
      loading:false,
      tableData: [],
      currentPage: 1,
      pageSize: 6,
      totalItems: 0// 你的数据总条数,
      ,companyId:''

    }
  },
  mounted() {
    apiUser.getInfo().then(res=>{
      this.companyId=res.data.cid[0]
      // ElMessage.success('获取公司id成功'+this.companyId)
      this.LoadJobs(this.companyId)
    }).catch(err=>{
      // ElMessage.error
    })
      // this.LoadJobs()
      this.loading = true;

    setTimeout(()=>{
      this.loading=false
    },500)

  },
  methods:
      {
        formatDate(date) {
          const year = date.getFullYear();
          const month = (date.getMonth() + 1).toString().padStart(2, '0');
          const day = date.getDate().toString().padStart(2, '0');

          const hours = date.getHours().toString().padStart(2, '0');
          const minutes = date.getMinutes().toString().padStart(2, '0');
          const seconds = date.getSeconds().toString().padStart(2, '0');
          var res = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
          // ElMessage.success(res)
          return res;
        },
        LoadJobs(id){
          // this.loading=true
          // console.log("idididi="+id)
          // ElMessage.success("查询岗位")
          api.get("/company/jobs",{
            params:{
              companyId:parseInt(id)
            }}
          ).then((res)=>{
            this.jobs = res
            ElMessage.success('查询成功')
            this.tableData=JSON.parse(JSON.stringify(this.jobs))
            this.totalItems = this.tableData.length
          }).catch((err)=>{
            ElMessage.error(err)
          })
          // this.loading=false
        },
        handleNewCareer() {
          api.post('/job',{
            careerID:0,
            corporationidcorporation:parseInt(this.companyId),
            careerName: this.newCareerEntry.name,
            workyear: this.newCareerEntry.workExperience,
            educationlevel: this.newCareerEntry.educationRequirement,
            workplace: this.newCareerEntry.JobLocation.toString().replace(',',"/"),
            salarymax:this.newCareerEntry.upper,
            salarymin:this.newCareerEntry.lower,
            status: this.newCareerEntry.status,
            careerInfo:this.newCareerEntry.info,
            creatdtime:this.formatDate(new Date()),
          }).then((res)=>{
            // ElMessage.success('新增成功')
            this.LoadJobs(this.companyId)
          }).catch((err)=>{
            ElMessage.error(err)
          })
          ElMessage.success("新增岗位" + this.newCareerEntry.name)
          this.newCareer=false
          this.newCareerEntry = {
            name: "",
            workExperience: "",
            educationRequirement: "",
            JobLocation:"",
            upper:0,
            lower:0,
            status: 0,
            info:"",
          }
        },
        handleSubmitEdit()
        {
          api.put('/job/'+this.editCareerEntry.careerID,{
            careerID:this.editCareerEntry.careerID,
            corporationidcorporation:parseInt(this.companyId),
            careerName: this.editCareerEntry.careerName,
            workyear: this.editCareerEntry.workyear,
            educationlevel: this.editCareerEntry.educationlevel,
            workplace: this.editCareerEntry.workplace.toString().replace(',',"/"),
            salarymax:this.editCareerEntry.salarymax,
            salarymin:this.editCareerEntry.salarymin,
            status: this.editCareerEntry.status,
            careerInfo:this.editCareerEntry.careerInfo,
            creatdtime:this.formatDate(new Date()),
          }).then((res)=>{
            // ElMessage.success('修改成功')
            this.LoadJobs(this.companyId)
          }).catch((err)=>{
            ElMessage.error(err)
          })
          ElMessage.success("修改岗位" + this.editCareerEntry.careerName)
          this.editCareer=false
        }
        ,
        handleEdit(row) {
          // console.log(index, row)
          // this.editCareerEntry = row
          // ElMessage.success("编辑" + row.careerName+"工作经验"+row.workyear+"学历要求"+row.educationlevel+"工作地点"+row.workplace+"月薪"+row.salarymin+"K~"+row.salarymax+"K"+"状态"+row.status)
          this.editCareerEntry = row
          this.editCareerEntry.workplace = row.workplace.split("/")
          this.editCareerEntry.lower = row.salarymin
          this.editCareerEntry.upper = row.salarymax
          this.editCareerEntry.careerInfo = row.careerInfo
          this.editCareerEntry.careerID = row.careerID
          this.editCareerEntry.careerName = row.careerName
          this.editCareerEntry.workyear = row.workyear
          this.editCareerEntry.educationlevel = row.educationlevel
          this.editCareerEntry.status = row.status
          // this.editCareerEntry.creatdtime = row.creatdtime
          this.editCareer = true
          // this.editCareerEntry = row
          // this.newCareer=false
          // //post

          // this.editCareer = true

        },
        handleDelete(row) {
          // ElMessage.success("删除" + row.careerName)
          // this.newCareer=false
          // ElMessage.success("删除" + row.careerID)
          api.delete('/job/'+row.careerID,
          ).then((res)=>{
            // ElMessage.success("删除成功")
            ElMessage.success(res.code.toString())
            this.LoadJobs(this.companyId)
          }).catch(error => {
            ElMessage.error(error.code)
            // ElMessage.error("删除"+row.stuId+"失败")
            // ElMessage.error("删除失败")
          },)
        },
        handlePreview(row) {
          this.Preview = true
          this.Curjob = row
        },
        handleExitNew(){
          this.newCareer=false
          this.newCareerEntry = {
            name: "",
            workExperience: "",
            educationRequirement: "",
            JobLocation: [],
            upper:0,
            lower:0,
            status: 0,
            info:"",
          }
        },
        handleExitEdit(){
          this.editCareer=false
        },
        getImprovedEdit(){
          let info = '工作岗位'+this.editCareerEntry.careerName+'工作经验'+this.editCareerEntry.workyear+'学历要求'+this.editCareerEntry.educationlevel+'工作地点'+this.editCareerEntry.workplace+'月薪'+this.editCareerEntry.salarymin+'K~'+this.editCareerEntry.salarymax+'K'+'职位简介'+this.editCareerEntry.careerInfo
          this.editCareerEntry.careerInfo = this.getImproved(info)
        },
        getImprovedNew(){
          let info = '工作岗位'+this.newCareerEntry.name+'工作经验'+this.newCareerEntry.workExperience+'学历要求'+this.newCareerEntry.educationRequirement+'工作地点'+this.newCareerEntry.JobLocation+'月薪'+this.newCareerEntry.lower+'K~'+this.newCareerEntry.upper+'K'+'职位简介'+this.newCareerEntry.info
          this.newCareerEntry.info = this.getImproved(info)
        },
        getImproved(info){
          var form = new FormData()
          form.append('message',info)
          axios.post('http://127.0.0.1:7000/ai/improve',
              form).then((res)=>{
            // console.log(res)
            return res.data.message
          }).catch((err)=>{
            ElMessage.error(err.code)
          })
          return "暂无"
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
        岗位管理
      </template>
      <template #content>
        管理企业发布的岗位信息
      </template>
    </PageHeader>
    <PageMain>
      <template #title>
        <div>
          <el-button type="primary" @click="newCareer=true">
            <SvgIcon name="gridicons:add-outline"/>
            &nbsp;新增岗位</el-button>
          <!--        <el-button type="primary" @click="testRoute">测试路由</el-button>-->
        </div>
      </template>
      <template #default>
        <el-row justify="space-between" >
          <el-col v-for="job in currentPageData"  :xs="4" :sm="6" :md="10" :lg="12" :xl="11">
            <el-card  style="margin-bottom: 20px; width: 600px" >
            <el-skeleton :rows="3" animated :loading="loading">
              <template #default>
            <el-row justify="space-between">
              <el-col :span="100">
              <el-tag v-if="job.status === 0" type="danger" size="large">未发布</el-tag>
              <el-tag v-else-if="job.status === 1" type="success" size="large">已发布</el-tag>
              </el-col>
              <el-col :span="100">
<!--                  <h1> -->
                   <el-text type="info" style="color:#000;"><h1>{{job.careerName}}</h1></el-text>
              </el-col>
            </el-row>
            <el-divider direction="horizontal" ></el-divider>
            <el-row justify="space-between">
              <el-col :span="100">
                <el-space size="small" :spacer="spacer" >
                  <el-button type="primary" link @click="handlePreview(job)" size="large">
                    <!--              <SvgIcon icon-class="material-symbols:search" />-->
                    <SvgIcon name="ion:eye-outline"/>
                    查看
                  </el-button>
                  <el-button type="warning" link @click="handleEdit(job)" size="large">
                                  <SvgIcon name="tabler:edit" />
                    编辑
                  </el-button>
                  <el-button type="danger" link @click="handleDelete(job)" size="large">
                                  <SvgIcon name="material-symbols-light:delete-outline" />
                    删除
                  </el-button>
                </el-space>
              </el-col>
              <el-col :span="8">
                <el-tag size="small" >更新时间：{{ job.creatdtime }}</el-tag>
              </el-col>
            </el-row>
              </template>
            </el-skeleton>
          </el-card>

        </el-col>
        </el-row>
        <el-pagination
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 50]"
            :page-size="pageSize"
            :total="totalItems"
        ></el-pagination>
<!--        </el-scrollbar>-->
      </template>
    </PageMain>

    <JobDetailsDialog
        v-model:modelValue="Preview"
        title="职位详情"
        :positionTitle="Curjob.careerName"
        :salary="Curjob.salarymin+'K~'+Curjob.salarymax+'K'"
        :jobLocation="Curjob.workplace"
        :educationRequirement="Curjob.educationlevel"
        :workExperience="Curjob.workyear"
        :job-intro-text="Curjob.careerInfo"
    >
    </JobDetailsDialog>
    <HDialog v-model="newCareer" @cancel="handleExitNew" title="新增岗位">
      <template #default>
        <el-form label-width="80px">
          <el-form-item label="岗位名称">
            <el-input v-model="newCareerEntry.name"></el-input>
          </el-form-item>
          <el-form-item label="工作经验">
            <el-select v-model="newCareerEntry.workExperience" placeholder="请选择">
              <el-option
                  v-for="item in workExperiences"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学历要求">
            <el-select v-model="newCareerEntry.educationRequirement" placeholder="请选择">
              <el-option
                  v-for="item in educationRequirements"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="工作地点">
            <!--          <p>省份/城市联动</p>-->
            <PcasCascader v-model="newCareerEntry.JobLocation" type="pc" format="name" />
          </el-form-item>
          <el-form-item label="月薪">
            <el-input-number v-model="newCareerEntry.lower" :min="0" :max="newCareerEntry.upper" controls-position="right" />
            <el-text >&nbsp;K&nbsp;~&nbsp;</el-text>
            <el-input-number v-model="newCareerEntry.upper" :min = "newCareerEntry.lower" :max="100" controls-position="right"/>
            <el-text >&nbsp;K</el-text>
          </el-form-item>
          <el-form-item label="职位简介">
            <el-input type="textarea" v-model="newCareerEntry.info" :autosize="{ minRows: 4, maxRows: 6}"
                      show-word-limit maxlength="1500"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" plain round @click="getImprovedEdit" >AI润色</el-button>
          </el-form-item>
          <el-form-item label="状态">
            <el-radio-group v-model="newCareerEntry.status">
              <el-radio label="1" >发布</el-radio>
              <el-radio label="0" >保存</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <el-button  @click="handleExitNew">取消</el-button>
        <el-button type="primary" @click="handleNewCareer">确定</el-button>
      </template>
    </HDialog>

    <HDialog v-model="editCareer" @cancel="handleExitEdit" title="编辑岗位">
      <template #default>
        <el-form label-width="80px">
          <el-form-item label="岗位名称">
            <el-input v-model="editCareerEntry.careerName"></el-input>
          </el-form-item>
          <el-form-item label="工作经验">
            <el-select v-model="editCareerEntry.workyear" placeholder="请选择">
              <el-option
                  v-for="item in workExperiences"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学历要求">
            <el-select v-model="editCareerEntry.educationlevel" placeholder="请选择">
              <el-option
                  v-for="item in educationRequirements"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="工作地点">
            <!--          <p>省份/城市联动</p>-->
            <PcasCascader v-model="editCareerEntry.workplace" type="pc" format="name" :placeholder="editCareerEntry.workplace"/>
          </el-form-item>
          <el-form-item label="月薪">
            <el-input-number v-model="editCareerEntry.salarymin" :min="0" :max="editCareerEntry.salarymax" controls-position="right" />
            <el-text >&nbsp;K&nbsp;~&nbsp;</el-text>
            <el-input-number v-model="editCareerEntry.salarymax" :min = "editCareerEntry.salarymin" :max="100" controls-position="right"/>
            <el-text >&nbsp;K</el-text>
          </el-form-item>
          <el-form-item label="职位简介">
            <el-input type="textarea" v-model="editCareerEntry.careerInfo" :autosize="{ minRows: 4, maxRows: 6}"
                      show-word-limit maxlength="500"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" plain round @click="getImprovedNew">AI润色</el-button>
          </el-form-item>
          <el-form-item label="状态">
            <el-radio-group v-model="editCareerEntry.status">
              <el-radio label="1" >发布</el-radio>
              <el-radio label="0" >保存</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <el-button  @click="handleExitNew">取消</el-button>
        <el-button type="primary" @click="handleSubmitEdit">确定</el-button>
      </template>
    </HDialog>
  </div>
</template>

<style scoped lang="scss">

</style>
