<!--<template>-->
<!-- <div>-->
<!--   <PageHeader title="问卷调研">-->
<!--     <template #content>-->

<!--     </template>-->
<!--   </PageHeader>-->
<!--   <PageMain>-->
<!--     <template #default>-->
<!--&lt;!&ndash;       <el-button @click="preview"/>&ndash;&gt;-->
<!--      -->
<!--     </template>-->
<!--   </PageMain>-->

<!-- </div>-->
<!--</template>-->

<!--<script setup>-->
<!--</script>-->
<!--<script>-->
<!--export default {-->
<!--  name: "questionnaire",-->
<!--  data() {-->
<!--    return {-->
<!--      url:'http://127.0.0.1:3000/preview/',-->
<!--      fileName: "vform68915"-->
<!--      // formJson:{}-->
<!--    }-->
<!--  },-->
<!--  methods:{-->
<!--    preview(){-->
<!--      this.url=this.url+fileName-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->


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
// import axios from "axios";
import api from "@/api";
import {formatDate} from "@vueuse/core";
import {computed} from "vue";
import axios from "axios";
export default {

  data() {
    return {
      // workExperiences: [
      //   { value: "不限", label: "不限" },
      //   { value: "在校生", label: "在校生" },
      //   { value: "应届生", label: "应届生" },
      //   { value: "经验不限", label: "经验不限" },
      //   { value: "1年以内", label: "1年以内" },
      //   { value: "1-3年", label: "1-3年" },
      //   { value: "3-5年", label: "3-5年" },
      //   { value: "5-10年", label: "5-10年" },
      //   { value: "10年以上", label: "10年以上" }
      // ],
      // educationRequirements:[
      //   { value: "不限", label: "不限" },
      //   { value: "初中及以下", label: "初中及以下" },
      //   { value: "中专/中技", label: "中专/中技" },
      //   { value: "高中", label: "高中" },
      //   { value: "大专", label: "大专" },
      //   { value: "本科", label: "本科" },
      //   { value: "硕士", label: "硕士" },
      //   { value: "博士", label: "博士" }
      // ],
      Preview: false,
      newCareer: false,
      editCareer: false,
      // tableData: [
      //   {id: 3, name: '岗位3', time: '2021-05-01', status: 1},
      //   {id: 2, name: '岗位2', time: '2021-05-01', status: 1},
      //   {id: 1, name: '岗位1', time: '2021-05-01', status: 0},
      // ],
      newemployeesEntry: {
        idquestionnaire:0,
        fileName: "",
        intro: "",
        title: "",
        state: 1,
        creattime: "",
        userName: ""
      },
      // jobs:[],
      employees:[
        {
          idquestionnaire: 12,
          fileName: "vform103235",
          intro: "这一个空白问卷哈",
          title: "空白问卷嗷",
          state: 1,
          creattime: "2024-01-08 17:23:22",
          userName: "hhh"
        },
        {
          idquestionnaire: 12,
          fileName: "vform103235",
          intro: "这一个空白问卷哈",
          title: "空白问卷嗷",
          state: 1,
          creattime: "2024-01-08 17:23:22",
          userName: "hhh"
        },
        {
          idquestionnaire: 12,
          fileName: "vform103235",
          intro: "这一个空白问卷哈",
          title: "空白问卷嗷",
          state: 1,
          creattime: "2024-01-08 17:23:22",
          userName: "hhh"
        },
        {
          idquestionnaire: 12,
          fileName: "vform103235",
          intro: "这一个空白问卷哈",
          title: "空白问卷嗷",
          state: 1,
          creattime: "2024-01-08 17:23:22",
          userName: "hhh"
        },
        {
          idquestionnaire: 12,
          fileName: "vform103235",
          intro: "这一个空白问卷哈",
          title: "空白问卷嗷",
          state: 1,
          creattime: "2024-01-08 17:23:22",
          userName: "hhh"
        },
        {
          idquestionnaire: 12,
          fileName: "vform103235",
          intro: "这一个空白问卷哈",
          title: "空白问卷嗷",
          state: 1,
          creattime: "2024-01-08 17:23:22",
          userName: "hhh"
        },
        // {
        //     "stuName": "string",
        //     "careerName": "string",
        //     "stuId": "string",
        //     "workyear": "string",
        //     "age": "string",
        //     "educationlevel": "string"
        // }
        // {
        //   stuName: "张三",
        //   careerName: "前端开发工程师",
        //   stuId: "2018111111",
        //   workyear: "1年",
        //   age: "20",
        //   educationlevel: "本科"
        // },
        // {
        //   stuName: "张三",
        //   careerName: "前端开发工程师",
        //   stuId: "2018111111",
        //   workyear: "1年",
        //   age: "20",
        //   educationlevel: "本科"
        // },
        // {
        //   stuName: "张三",
        //   careerName: "前端开发工程师",
        //   stuId: "2018111111",
        //   workyear: "1年",
        //   age: "20",
        //   educationlevel: "本科"
        // },
        // {
        //   stuName: "张三",
        //   careerName: "前端开发工程师",
        //   stuId: "2018111111",
        //   workyear: "1年",
        //   age: "20",
        //   educationlevel: "本科"
        // },
        // {
        //   stuName: "张三",
        //   careerName: "前端开发工程师",
        //   stuId: "2018111111",
        //   workyear: "1年",
        //   age: "20",
        //   educationlevel: "本科"
        // },
        // {
        //   stuName: "张三",
        //   careerName: "前端开发工程师",
        //   stuId: "2018111111",
        //   workyear: "1年",
        //   age: "20",
        //   educationlevel: "本科"
        // },
        // {
        //   stuName: "张三",
        //   careerName: "前端开发工程师",
        //   stuId: "2018111111",
        //   workyear: "1年",
        //   age: "20",
        //   educationlevel: "本科"
        // },
        // {
        //   stuName: "张三",
        //   careerName: "前端开发工程师",
        //   stuId: "2018111111",
        //   workyear: "1年",
        //   age: "20",
        //   educationlevel: "本科"
        // }


      ],
      loading:false,
      tableData: [],
      currentPage: 1,
      pageSize: 6,
      totalItems: 0// 你的数据总条数

    }
  },
  mounted() {
    // this.LoadQuestions()
    this.loading = true;
    this.tableData=JSON.parse(JSON.stringify(this.employees))
    this.totalItems = this.tableData.length
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
          ElMessage.success(res)
          return res;
        },
        LoadQuestions(){
          // this.loading=true
          ElMessage.success("查询问卷")
          api.get("/questionnaires",{
            params:{
              title: "",
            }}
          ).then((res)=>{
            this.employees = res
            ElMessage.success('查询问卷')
          }).catch((err)=>{
            ElMessage.error(err)
          })
          // this.loading=false
        },
        handleCurrentChange(val) {
          this.loading=true
          this.currentPage = val;
        },
        handlePreview(row) {
          this.Preview = true
          this.Curjob = row
        },
        handleExitEdit(){
          this.editCareer=false
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
        问卷列表
      </template>
      <template #content>
        教务处发布的问卷将在这里呈现
      </template>
    </PageHeader>
    <PageMain>
      <template #default>
        <el-row justify="space-between" >
          <el-col v-for="employee in currentPageData" :key="employee.title">
            <el-card  style="margin-bottom: 20px" >
              <el-skeleton  animated :loading="loading" :rows="2">
                <template #default>
                  <el-row justify="space-around">
                    <el-col :span="3">
                      <el-image  style="width: 108px; height: 100px;margin-left: 10px" src="https://picst.sunbangyan.cn/2024/01/11/8d09f95c5883658aa79293b1dea385c4.jpeg" fit="cover"></el-image>
                    </el-col>
                    <!--                                   <el-col :span="12">-->
                    <!--                                     <el-tag type="success" style="margin-right: 10px">{{employee.careerName}}</el-tag>-->
                    <!--                                   </el-col>-->
                    <el-col :span="21">
                      <el-row justify="end">
                        <br><br>
                        <el-col :span="24">
                          <!--                                         <el-tag type="success" style="margin-right: 10px">{{employee.stuId}}</el-tag>-->
                          <el-text type="info" size="large" style="color: #1a1a1a;font-size: 20px">
                            <strong>
                              {{employee.title}}
                            </strong>
                          </el-text>
                        </el-col>
                        <!--                        <el-col :span="8">-->
                        <!--                          <span></span>-->
                        <!--                        </el-col>-->
                        <!--                        <el-col :span="8">-->
                        <!--                          <el-space spacer="|">-->
                        <!--                            <el-text  size="large" style="color:#000;font-size: 18px">-->
                        <!--                              <strong>-->
                        <!--                                创建者-->
                        <!--                              </strong>-->
                        <!--                            </el-text>-->
                        <!--                            <el-text type="warning" size="large" style="font-size: 17px">-->
                        <!--                              <strong>-->
                        <!--                                {{employee.userName}}-->
                        <!--                              </strong>-->

                        <!--                            </el-text>-->
                        <!--                          </el-space>-->

                        <!--                        </el-col>-->
                        <!--                        <br><br>-->
                        <el-col :span="24">
                          <el-text type="info">
                            <strong>
                              问卷描述：{{employee.intro}}
                            </strong>
                          </el-text>
                        </el-col>
                        <br><br>
                        <!--                        <el-col :span="11" style="display: flex; flex-direction: column;">-->
                        <!--                          <el-tag type="success" style="font-size: 12px; width: 150px;margin-top: 10px">{{employee.fileName}}</el-tag>-->
                        <!--                        </el-col>-->

                        <el-col :span="8">
                          <span></span>
                        </el-col>
                        <el-col :span="5">
                          <el-button size="large" style="font-size: 17px;margin-top: 25px" link type="primary" @click="handlePreview(employee)">
                            <SvgIcon name="fontisto:preview"/>
                            &nbsp;查看问卷
                          </el-button>
                        </el-col>
                      </el-row>
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
  </div>
</template>

<style scoped>
.skeleton-item {
  display: flex;
  align-items: center;
  padding: 20px;
}

.left {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background-color: #ccc;
}

.right {
  margin-left: 20px;
}
</style>
