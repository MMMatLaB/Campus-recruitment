<script setup lang="ts">

</script>
<script>
import api from "@/api";
import apiUser from "../../../api/modules/user";
import MarkdownPreview from '@uivjs/vue-markdown-preview';

import {ElMessage} from "element-plus";
export default {
  name: 'Resume',
  components: {
    MarkdownPreview
  },
  data() {
    return {
      suggestion:"这是一句话",
      colors :['#99A9BF', '#F7BA2A', '#FF9900'],
      editDialogVisible: false,
      toBeImproved:[],
      editResume:{
        name:'',
        age:0,
        educationlevel:'',
        workplace:'',
        idealcareer:'',
        idealdomain:"",
        idealcity:[],
        idealsalary:'',
        workyear:"",
        telephone:'',
        description:''
      },
        resume:{} ,
      resumeExisted: true,
      salaries:[
        { value: "3k以下", label: "3k以下" },
        { value: "3k-5k", label: "3k-5k" },
        { value: "5k-10k", label: "5k-10k" },
        { value: "10k-15k", label: "10k-15k" },
        { value: "15k-20k", label: "15k-20k" },
        { value: "20k-25k", label: "20k-25k" },
        { value: "25k-30k", label: "25k-30k" },
        { value: "30k-50k", label: "30k-50k" },
        { value: "50k以上", label: "50k以上" }
      ],
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
      workExperiences: [
        { value: "在校生", label: "在校生" },
        { value: "应届生", label: "应届生" },
        // { value: "经验不限", label: "经验不限" },
        { value: "1年以内", label: "1年以内" },
        { value: "1-3年", label: "1-3年" },
        { value: "3-5年", label: "3-5年" },
        { value: "5-10年", label: "5-10年" },
        { value: "10年以上", label: "10年以上" }
      ],
      educationRequirements:[
        { value: "初中及以下", label: "初中及以下" },
        { value: "中专/中技", label: "中专/中技" },
        { value: "高中", label: "高中" },
        { value: "大专", label: "大专" },
        { value: "本科", label: "本科" },
        { value: "硕士", label: "硕士" },
        { value: "博士", label: "博士" }
      ],
      userId: 0,
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    // console.log(this.suggestion)
    apiUser.getInfo().then(res => {
      this.userId = res.data.userid[0]
      // ElMessage.success('获取uid成功' + this.userId)
      this.LoadResume()
    })

  },
  methods: {
    getSuggestion(){

    },
    LoadResume() {
      api.get('/resume',{
        params:{
          userName:this.userId
        }
      }).then((res)=> {
          // ElMessage.success(res.code)
        if(res){
          this.resumeExisted = false
          this.resume = res
          this.resume.score = this.resume.score/20
          this.editResume = JSON.parse(JSON.stringify(this.resume))
          if(this.resume.age===''){
            this.toBeImproved.push("年龄")
            this.resume.age='未填写'
          }
          if(!this.resume.workplace===''){
            this.toBeImproved.push("工作地点")
            this.resume.workplace='未填写'
          }
          if(this.resume.educationlevel===''){
            this.toBeImproved.push("学历")
            this.resume.educationlevel='未填写'
          }
          if(this.resume.workyear===''){
            this.toBeImproved.push("工作经验")
            this.resume.workyear='未填写'
          }
          if(this.resume.idealcity===''){
            this.toBeImproved.push("意向城市")
            this.resume.idealcity='未填写'
          }
          if(this.resume.idealsalary===''){
            this.toBeImproved.push("意向薪资")
            this.resume.idealsalary='未填写'
          }
          if(this.resume.idealcareer===''){
            this.toBeImproved.push("意向职位")
            this.resume.idealcareer='未填写'
          }
          if(this.resume.idealdomain===''){
            this.toBeImproved.push("意向行业")
            this.resume.idealdomain='未填写'
          }
          if(this.resume.description===''){
            this.toBeImproved.push("个人简介")
            this.resume.description='未填写'
          }
          // ElMessage.success('获取简历成功')

        }
        else{
          this.resumeExisted = true
          // ElMessage.error("Dada")
        }
      })
    },
    test(){
      this.resumeExisted = false
    },
    updateResume(){
      this.$router.push({path:'/school_job/editResume'})
    },
    submitResume()
    {
      this.editDialogVisible= false
      //post
      this.resumeExisted = false
      this.resume=JSON.parse(JSON.stringify(this.editResume))
    }
  },
};
</script>
<template>
<div>
  <PageHeader title="我的简历">
    <template #content>
    在这里可以查看和编辑你的简历，努力完善简历，让企业更好的了解你
    </template>
  </PageHeader>
  <PageMain>
    <template #title>
      <h2>简历信息</h2>
      <el-empty style="height: 500px" description="还没有简历，快来创建吧！" v-if="resumeExisted">
        <el-button type="primary" @click="updateResume" v-if="resumeExisted">创建简历</el-button>
      </el-empty>
    </template>
    <template #default>

      <div class="resume" v-if="!resumeExisted">
      <el-descriptions
          title="基本信息"
        class="margin-top"
        :column="3"
          :size="'default'"
        border
      >
        <el-descriptions-item>
          <template #label >
            <div class="cell-item"  style="text-align: center">
              <el-space justify="center">
                  <SvgIcon name="clarity:user-line" />
                姓名
              </el-space>
            </div>
          </template>
            {{resume.rname}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label >
            <div class="cell-item"  style="text-align: center">
              <el-space justify="center">
                  <SvgIcon name="mingcute:hat-fill" />
                年龄
              </el-space>
            </div>
          </template>
          {{resume.age}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item" style="text-align: center">
             <SvgIcon name="bytesize:telephone"></SvgIcon>
              电话
            </div>
          </template>
          {{resume.rtel}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item"  style="text-align: center">
              <SvgIcon name="cil:education"></SvgIcon>
              学历
            </div>
          </template>
          {{resume.educationlevel}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item"  style="text-align: center">
              <SvgIcon name="carbon:tool-kit"></SvgIcon>
              经验
            </div>
          </template>
          {{resume.workyear}}
        </el-descriptions-item>
      </el-descriptions>
      <el-divider direction="horizontal" border-style="hidden"></el-divider>
      <el-descriptions title="求职意向" class="margin-top" :column="2" :size="'default'" border>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item"  style="text-align: center">
                            <SvgIcon name="ic:outline-place"></SvgIcon>
              地点
            </div>
          </template>
          {{resume.idealcity}}
        </el-descriptions-item>
        <el-descriptions-item >
          <template #label>
            <div class="cell-item" style="text-align: center">
                            <SvgIcon name="ri:space-ship-line"></SvgIcon>
              行业
            </div>
          </template>
          <!--          <el-tag size="small">School</el-tag>-->
          {{resume.idealcareer}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item" style="text-align: center">
                            <SvgIcon name="lets-icons:tie"></SvgIcon>
              职位
            </div>
          </template>
          <!--          <el-tag size="small">School</el-tag>-->
          {{resume.idealcareer}}
        </el-descriptions-item>
        <el-descriptions-item >
          <template #label>
            <div class="cell-item" style="text-align: center">
                            <SvgIcon name="bi:cash-coin"></SvgIcon>
              薪资
            </div>
          </template>
          <!--          <el-tag size="small">School</el-tag>-->
          {{resume.idealsalary}}
        </el-descriptions-item>
      </el-descriptions>
        <el-divider direction="horizontal" border-style="hidden"></el-divider>
        <el-descriptions title="个人简介" class="margin-top" :size="'default'" border >
      </el-descriptions>

<!--        <div style="font-family: sans-serif;font-size:14px" v-html="resume.description.replace('/\n/g','<br>')"></div>-->
        <pre><p style="font-family: sans-serif ;font-size: 16px" v-html="resume.description"></p></pre>
      </div>
<!--      <el-button type="primary" @click="updateResume" v-if="!resumeExisted" >编辑简历</el-button>-->
      <FixedActionBar @click="updateResume" v-if="!resumeExisted" >
      <el-button type="primary" size="large">
        编辑简历
      </el-button>
      </FixedActionBar>
    </template>
  </PageMain>
  <PageMain>
    <template #title>
      <h2>
        交小职
        <el-tag type="success" size="large" style="font-size: 20px">Beta</el-tag>
      </h2>
     <el-row>
       <el-col>
         交小职，你的职业导航！为西交大学子度身打造，提供个性求职和职业规划建议。
       </el-col>
       <el-col>
         无论你是在读生还是毕业生，交小职都是你值得依赖的伙伴。
       </el-col>
       <el-col>
         和交小职一起，开启你的职业征程吧！🌠
       </el-col>
     </el-row>
    </template>
    <template #default>
      <el-row :gutter="20">
        <el-col>
          <el-text >
            <h2>简历分析</h2>
          </el-text>
        </el-col>
        <el-col>
          <el-text size="large">
            简历得分:
          </el-text>
          <el-rate
              v-model="resume.score"
              disabled
              show-score
              text-color="#ff9900"
              score-template="{value}分"
          />
        </el-col>
        <el-col>
          <el-text size="large">
            待完善项:
          </el-text>
          <el-space size="small">
            <el-tag v-for="item in toBeImproved" style="font-size: 12px"
                    :key="item"
                    type="danger">{{item}}</el-tag>
          </el-space>

        </el-col>
      </el-row>
      <el-divider direction="horizontal" border-style="hidden"></el-divider>
      <el-row>
        <el-col>
          <el-text>
            <h2>求职建议&nbsp;<el-button type="primary" plain round @click="getSuggestion">获取</el-button></h2>
          </el-text>
        </el-col>
        <el-col >
          <el-scrollbar >
            <div style=" height: auto;">
              <el-input type="textarea" :rows="6" :readonly="true" :model-value="suggestion">
              </el-input>
            </div>
          </el-scrollbar>

        </el-col>

      </el-row>

    </template>
  </PageMain>
</div>
</template>

<style scoped lang="scss">

pre{

  white-space:pre-wrap;

  white-space:-moz-pre-wrap;

  white-space:-pre-wrap;

  white-space:-o-pre-wrap;

  word-wrap:break-word;

}</style>
