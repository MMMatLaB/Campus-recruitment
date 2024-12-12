<template>
  <div>
    <PageHeader title="推荐岗位">
      <template #content>
        人工智能大数据算法岗位推荐，根据学生的专业、学历、工作经验等信息，推荐适合的岗位
      </template>
    </PageHeader>
    <PageMain>
      <template #title>
        <el-form :inline="true" :model="searchParams" style="width: 1500px;">
          <el-form-item label="岗位名称">
            <el-input v-model="searchParams.name" placeholder="岗位，模糊查询"/>
          </el-form-item>
          <el-form-item label="学历要求">
            <!--            <el-input v-model="searchParams.educationRequirement" placeholder="姓名，模糊查询"/>-->
            <el-select v-model="searchParams.educationRequirement" placeholder="请选择学历">
              <el-option
                v-for="item in educationRequirements"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="工作经验">
            <el-select v-model="searchParams.workExperience" placeholder="请选择工作经验">
              <el-option
                v-for="item in workExperiences"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button @click="searchJobs">查询</el-button>
          </el-form-item>
        </el-form>
      </template>
      <template #default>
        <el-empty style="height: 500px" description="这里空空如也，试着搜搜其他的吧" v-if="recommendedJobs.length==0">
        </el-empty>
        <div v-if="recommendedJobs.length!=0">
          <el-container>
            <!-- 左侧滑动工作推荐卡 -->
            <el-scrollbar height="600px">

              <el-aside width="400px">
                <el-space direction="vertical" size="large">
                  <div v-for="job in recommendedJobs">
                    <el-card style="width: 380px;height: 200px">
                      <el-skeleton :rows="4" animated :loading="loading">
                        <template #default>
                          <el-row>
                            <el-col :span="18">
                              <el-text size="large">{{ job.title }}</el-text>
                            </el-col>
                            <el-col :span="5">
                              <el-text style='color:orangered;font-family: "Andale Mono"' size="large">{{
                                  job.salary
                                }}
                              </el-text>
                              <!--                              <span>{{ job.salary }}</span>-->
                            </el-col>
                          </el-row>
                          <el-divider direction="horizontal"/>
                          <el-row class="job-info" justify="start">
                            <el-col :span="24">
                              <el-space>
                                <el-tag type="success">{{ job.location }}</el-tag>
                                <el-tag>{{ job.requirements }}</el-tag>
                                <el-tag type="warning">{{ job.workExperience }}</el-tag>
                              </el-space>
                            </el-col>
                          </el-row>
                          <el-divider direction="horizontal"></el-divider>
                          <el-row>
                            <el-col :span="18">
                              <el-text type="info" size="default">{{ job.company }}</el-text>
                            </el-col>
                            <el-col :span="2">
                              <el-button plain type="primary" size="small" @click="handleApply(job)">查看详情
                              </el-button>
                            </el-col>
                          </el-row>
                        </template>
                      </el-skeleton>
                    </el-card>

                  </div>
                </el-space>
              </el-aside>
            </el-scrollbar>
            <!--              <el-divider direction="vertical"></el-divider>-->
            <el-container>
              <el-main>
                <el-card :key="Curjob.id" style="height:100%">
                  <el-skeleton :rows="14" animated :loading="loadingDetail">
                    <template #default>
                      <el-col>
                        <el-row justify="space-between">
                          <el-col :span="20">
                            <el-row>
                              <h1>{{ Curjob.title }}&nbsp;
                                <span style="color:orangered;font-family: 'Andale Mono'">{{ Curjob.salary }}</span>
                              </h1>
                            </el-row>
                            <!--                            <el-divider border-style="none" direction="horizontal"></el-divider>-->
                            <el-row>
                              <el-col>
                                <el-space :size="small" >
                                  <!--                                <el-text type="info" size="default">{{ Curjob.company }}</el-text>-->
                                  <el-tag type="success">{{ Curjob.location }}</el-tag>
                                  <el-tag>{{ Curjob.requirements }}</el-tag>
                                  <el-tag type="warning">{{ Curjob.workExperience }}</el-tag>
                                </el-space>
                              </el-col>
                            </el-row>
                          </el-col>
                          <el-col :span="4">
                            <!--                            <el-divider border-style="none" direction="horizontal"></el-divider>-->
                            <div>&nbsp;</div>
                            <el-button plain type="primary" size="large" @click="wantJob(Curjob)">投递简历</el-button>
                            <!--                            <el-divider border-style="none" direction="horizontal"></el-divider>-->
                          </el-col>
                        </el-row>
                      </el-col>
                      <el-divider direction="horizontal"/>
                      <h4> 职位描述:</h4>
                      <div>
                        <el-row>
                          <el-col :span="24">
                            <!--                            <el-divider></el-divider>-->
                            <el-scrollbar height="300px">
                              <div v-html="Curjob.description.replace(/\n/g, '<br>')"></div>
                            </el-scrollbar>
                          </el-col>
                        </el-row>
                      </div>
                    </template>
                  </el-skeleton>
                  <!--                    <template #footer>-->
                  <!--                      <el-row>-->
                  <!--                        <el-col :span="18">-->
                  <!--                          <el-text type="info" size="default">{{ Curjob.company }}</el-text>-->
                  <!--                        </el-col>-->
                  <!--                        <el-col :span="2">-->
                  <!--                          <el-button plain  type="primary" size="small" @click="handleApply(Curjob)">查看详情</el-button>-->
                  <!--                        </el-col>-->
                  <!--                      </el-row>-->
                  <!--                    </template>-->
                </el-card>
              </el-main>
            </el-container>
          </el-container>
        </div>
      </template>
    </PageMain>
  </div>
</template>
<script setup lang="ts">
import {ElMessage} from 'element-plus'
import Icon from "@/views/component_basic_example/components/icon.vue";
import apiUser from "../../../api/modules/user";
import api from "../../../api";
</script>
<script lang="ts">
import {ElMessage} from 'element-plus'
import Icon from "@/views/component_basic_example/components/icon.vue";
import apiUser from "../../../api/modules/user";
import api from "../../../api";


export default {
  data() {
    return {
      loading: true,
      loadingDetail: true,
      userID: '',
      data1: [],
      Curjob: {
        id: 1,
        title: '前端工程师',
        company: '阿里巴巴',
        location: '杭州',
        requirements: '本科及以上',
        workExperience: '3年以上',
        description: '处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
          '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
          '建立良好的客户关系，提高客户满意度和忠诚度"+"处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
          '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
          '建立良好的客户关系，提高客户满意度和忠诚度' + '处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
          '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
          '建立良好的客户关系，提高客户满意度和忠诚度"+"处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
          '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
          '建立良好的客户关系，提高客户满意度和忠诚度' + '处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
          '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
          '建立良好的客户关系，提高客户满意度和忠诚度"+"处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
          '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
          '建立良好的客户关系，提高客户满意度和忠诚度' + '处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
          '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
          '建立良好的客户关系，提高客户满意度和忠诚度"+"处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
          '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
          '建立良好的客户关系，提高客户满意度和忠诚度',

        salary: '10-20K'
      },
      workExperiences: [
        {value: "不限", label: "不限"},
        {value: "在校生", label: "在校生"},
        {value: "应届生", label: "应届生"},
        {value: "经验不限", label: "经验不限"},
        {value: "1年以内", label: "1年以内"},
        {value: "1-3年", label: "1-3年"},
        {value: "3-5年", label: "3-5年"},
        {value: "5-10年", label: "5-10年"},
        {value: "10年以上", label: "10年以上"}
      ],
      educationRequirements: [
        {value: "不限", label: "不限"},
        {value: "初中及以下", label: "初中及以下"},
        {value: "中专/中技", label: "中专/中技"},
        {value: "高中", label: "高中"},
        {value: "大专", label: "大专"},
        {value: "本科", label: "本科"},
        {value: "硕士", label: "硕士"},
        {value: "博士", label: "博士"}
      ],
      searchParams: {
        name: '',
        workExperience: '',
        educationRequirement: '',
      },
      recommendedJobs: [
        // {id: 1, title: '前端工程师', company: '阿里巴巴', location: '杭州', requirements: '本科及以上',workExperience:'3年以上', description: '处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
        //     '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
        //     '建立良好的客户关系，提高客户满意度和忠诚度',salary:'10-20K'},
        // {id: 2, title: '后端工程师', company: '腾讯', location: '深圳', requirements: '本科及以上',workExperience:'3年以上', description: '职位描述...',salary:'10-20K'},
        // {id: 3, title: 'UI/UX 设计师', company: '字节跳动', location: '北京', requirements: '本科及以上',workExperience:'3年以上', description: '职位描述...',salary:'10-20K'},
        // {id: 4, title: '产品经理', company: '百度', location: '北京', requirements: '本科及以上',workExperience:'3年以上', description: '职位描述...',salary:'10-20K'},
        // {id: 5, title: '运营', company: '美团', location: '上海', requirements: '本科及以上',workExperience:'3年以上', description: '职位描述...',salary:'10-20K'},
        // {id: 6, title: '测试', company: '滴滴', location: '成都', requirements: '本科及以上',workExperience:'3年以上', description: '职位描述...',salary:'10-20K'},
        // {id: 7, title: '算法工程师', company: '京东', location: '北京', requirements: '本科及以上',workExperience:'3年以上', description: '职位描述...',salary:'10-20K'},
        // {id: 8, title: '数据分析师', company: '小米', location: '北京', requirements: '本科及以上',workExperience:'3年以上', description: '处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
        //     '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
        //     '建立良好的客户关系，提高客户满意度和忠诚度"+"处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
        //     '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
        //     '建立良好的客户关系，提高客户满意度和忠诚度'+'处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
        //     '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
        //     '建立良好的客户关系，提高客户满意度和忠诚度"+"处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
        //     '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
        //     '建立良好的客户关系，提高客户满意度和忠诚度'+'处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
        //     '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
        //     '建立良好的客户关系，提高客户满意度和忠诚度"+"处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
        //     '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
        //     '建立良好的客户关系，提高客户满意度和忠诚度'+'处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
        //     '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
        //     '建立良好的客户关系，提高客户满意度和忠诚度"+"处理客户的咨询、投诉和问题，并提供满意的解决方案。\n' +
        //     '熟悉公司的产品和服务，为客户提供准确的信息和建议。\n' +
        //     '建立良好的客户关系，提高客户满意度和忠诚度',salary:'10-20K'},
      ],
      // selectedJob: null, // 选中的职位详情数据
    };
  },
  methods: {
    searchJobs() {
      api.get("/jobs", {
        params: {
          name: this.searchParams.name,
          workExperience: this.searchParams.workExperience,
          educationRequirement: this.searchParams.educationRequirement
        }
      }).then((res) => {
        //console.log(res)
        // ElMessage.success(res.length.toString())
        this.recommendedJobs=[]
        res.forEach(item=>{
          //console.log(item)
          // this.recommendedJobs=[]

          this.recommendedJobs.push(
            {
              title: item.careerName,
              company: item.companyName,
              location: item.workplace,
              requirements: item.educationlevel,
              workExperience: item.workyear,
              description: item.careerInfo,
              salary: item.salaryMin+'-'+item.salaryMax+' K',
              cid:item.cid
            }
          )
        })
        this.Curjob = this.recommendedJobs[0]
        console.log(this.recommendedJobs)
      })
    },
    // LoadRecommendedJobs() {
    //   apiUser.getInfo().then((res)=>{
    //     this.userID=res.data.userid[0]
    //
    //   }).then(()=>{
    //     //ElMessage.success(this.userID)
    //     api.get("/recommand",{
    //       params:{
    //         userName:this.userID
    //       }
    //     }).then((res)=>{
    //       console.log(res)
    //       ElMessage.success(res.length.toString())
    //       this.recommendedJobs.push(
    //         {title:res.name,company:res.companyName,location:res.jobLocation,requirements:res.educationRequirement,workExperience:res.workExperience,description:res.info,salary:res.salary}
    //       )
    //     })
    //   })
    //
    // },
    handleApply(job) {
      this.loadingDetail = true;
      // this.$router.push({ path: '/admin/user' });
      setTimeout(() => {
        this.loadingDetail = false
      }, 500)

      this.Curjob = job;

    },
    wantJob(){
      api.put('/wantjob',null,{
        params:{
          stuId:this.userID,
          careerId:this.Curjob.cid
        }
      }).then(res=>{
        ElMessage.success("简历投递成功")
      })
    }

  },
  mounted() {
    apiUser.getInfo().then(res=>{
      this.userID = res.data.userid[0]
      // this.recommendedJobs
      this.searchJobs()
      //this.LoadRecommendedJobs()
      // this.Curjob = this.recommendedJobs[0];
      this.loading = true
      this.loadingDetail = true
    })

    setTimeout(() => {
      this.loading = false;
      this.loadingDetail = false
    }, 500)
  },


  // 可以在这里调用接口或从其他地方获取数据，然后更新 recommendedJobs 和 selectedJob 数据
  // created() {
  //   // 示例：模拟异步获取工作推荐数据
  //   setTimeout(() => {
  //     this.recommendedJobs = [
  //       { id: 1, title: '前端工程师' },
  //       { id: 2, title: '后端工程师' },
  //       { id: 3, title: 'UI/UX 设计师' },
  //       // 其他工作推荐数据...
  //     ];
  //
  //     // 示例：模拟异步获取选中的职位详情数据
  //     this.selectedJob = { id: 1, title: '前端工程师', description: '职位详情...' };
  //   }, 1000);
  // },
};
</script>

<style scoped>

</style>
