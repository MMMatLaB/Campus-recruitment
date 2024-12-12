<script setup>
import JobDetailsDialog from "@/components/Window/JobDetailsDialog.vue";
import axios from "axios"
// const currentDate = new Date();
// const formattedDate = format(currentDate, 'yyyy-MM-dd HH:mm:ss');
</script>
<script>
// import { format } from 'date-fns';
import {ElMessage} from "element-plus";
// import axios from "axios";
import api from "@/api";
import {formatDate} from "@vueuse/core";
import {computed} from "vue";
import axios from "axios";
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
      loading:false,
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
      // jobs: [
      //   { name: "前端开发工程师",
      //     salary: "10K-20K",
      //     jobLocation: "北京",
      //     educationRequirement: "本科",
      //     workExperience: "3年以上",
      //     time: "2021-05-01",
      //     info: "职位简介:\n" +
      //       "我们正在寻找一位热爱数据的数据分析师，加入我们充满创新和活力的团队。作为数据分析师，你将负责收集、清洗和分析大量数据，为公司提供有价值的见解。你将参与制定数据分析策略，协助业务部门做出明智的决策。如果你熟练运用各种数据分析工具，具备优秀的沟通能力，并且对技术创新充满激情，那么我们期待你的加入！\n" +
      //       "\n" +
      //       "岗位要求:\n" +
      //       "    本科及以上学历，数学、统计学、计算机科学等相关专业优先考虑。\n" +
      //       "    2年以上数据分析经验，熟练使用数据分析工具如Python、R等。\n" +
      //       "    良好的沟通能力，能够将复杂的数据分析结果清晰呈现给非技术人员。\n" +
      //       "    对业务问题有敏锐的洞察力，能够为业务决策提供有力支持。\n" +
      //       "    积极主动，具备团队合作精神，对技术创新有强烈兴趣。"},
      //   {
      //     name: "后端开发工程师",
      //     salary: "10K-20K",
      //     jobLocation: "北京",
      //     educationRequirement: "本科",
      //     workExperience: "3年以上",
      //     time: "2021-05-01",
      //     info:"职位简介:"
      //   }
      // ],
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

  }
  },
  mounted() {
    this.LoadJobs()
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
  ElMessage.success(res)
  return res;
},
    LoadJobs(){
      this.loading=true
      ElMessage.success("查询岗位")
      api.get("/jobs",{
        params:{
          companyId:1,
        }}
      ).then((res)=>{
        this.jobs = res
        ElMessage.success('查询成功')
      }).catch((err)=>{
        ElMessage.error(err)
      })
      this.loading=false
    },
    handleNewCareer() {
      api.post('/job',{
        careerID:0,
        corporationidcorporation:1,
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
        this.LoadJobs()
      }).catch((err)=>{
        ElMessage.error(err)
      })
    //   ElMessage.success("新增岗位" + this.newCareerEntry.name
    //   +"工作经验"+this.newCareerEntry.workExperience+
    //   "学历要求"+this.newCareerEntry.educationRequirement+
    //   "工作地点"+this.newCareerEntry.JobLocation+
    //   "月薪"+this.newCareerEntry.lower+"K~"+this.newCareerEntry.upper+"K"+
    //   "状态"+this.newCareerEntry.status
    // )
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
    handleEdit(row) {
      // console.log(index, row)
      // this.editCareerEntry = row
      // ElMessage.success("编辑" + row.careerName+"工作经验"+row.workyear+"学历要求"+row.educationlevel+"工作地点"+row.workplace+"月薪"+row.salarymin+"K~"+row.salarymax+"K"+"状态"+row.status)
      this.editCareerEntry = row
      this.editCareerEntry.workplace =
      // ElMessage.success("编辑" + row.careerName)
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
        this.LoadJobs()
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
      getImproved(Entry){
        var message = "岗位名称:"+Entry.name+"\n工作经验:"+Entry.workExperience+"\n学历要求:"+Entry.educationRequirement+"\n工作地点:"+Entry.JobLocation+"\n月薪："+Entry.lower+"K~"+Entry.upper+"K\n"+"职位简介："+Entry.info
        // ElMessage.warning(message)
        axios.get('http://127.0.0.1:7000/ai/improve',{
          params:{
            message:message
          }
        }).then((res)=>{
          // ElMessage.success(res.code)
          Entry.careerInfo = res.message
        }).catch((err)=>{
          ElMessage.error(err.code)
        })
      }


    }
}

</script>
<template>
<div>
  <PageHeader>
    <template #title>
      人才库
    </template>
    <template #content>
      投递简历的学生信息将在这里呈现
    </template>
  </PageHeader>
  <PageMain>
    <template #default>
      <el-table :data="jobs" border style="width: 100%" v-loading="loading">
        <el-table-column type="index" label="序号" width="100">
        </el-table-column>
        <el-table-column prop="careerName" label="职位名称" width="180">
        </el-table-column>
        <el-table-column prop="creatdtime" label="修改时间"  width="280">
        </el-table-column>
        <el-table-column prop="status" label="状态" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.status === 0 ? 'danger' : 'success'">
              {{ scope.row.status === 0 ? '未发布' : '已发布' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="详情" width="100">
          <template #default="scope">
            <el-button type="text" @click="handlePreview(scope.row)">查看</el-button>
          </template>
          </el-table-column>
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
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
          show-word-limit maxlength="500"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" plain round @click="getImproved(newCareerEntry)" >AI润色</el-button>
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
          <el-button type="primary" plain round @click="getImproved(editCareerEntry)">AI润色</el-button>
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
      <el-button type="primary" @click="handleNewCareer">确定</el-button>
    </template>
  </HDialog>
</div>
</template>

<style scoped lang="scss">

</style>
