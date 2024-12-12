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
import apiUser from "@/api/modules/user";

export default {

  data() {
    return {
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
      Preview: false,
      newCareer: false,
      editCareer: false,
      cid: '',
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
        upper: 0,
        lower: 0,
        status: 0
      },
      // jobs:[],
      employees: [
        // {
        //     "stuName": "string",
        //     "careerName": "string",
        //     "stuId": "string",
        //     "workyear": "string",
        //     "age": "string",
        //     "educationlevel": "string"
        // }
        // {
        //     stuName: "张三",
        //     careerName: "前端开发工程师",
        //     stuId: "2018111111",
        //     workyear: "1年",
        //     age: "20",
        //     educationlevel: "本科"
        // },
        // {
        //     stuName: "张三",
        //     careerName: "前端开发工程师",
        //     stuId: "2018111111",
        //     workyear: "1年",
        //     age: "20",
        //     educationlevel: "本科"
        // },
        // {
        //     stuName: "张三",
        //     careerName: "前端开发工程师",
        //     stuId: "2018111111",
        //     workyear: "1年",
        //     age: "20",
        //     educationlevel: "本科"
        // },
        // {
        //     stuName: "张三",
        //     careerName: "前端开发工程师",
        //     stuId: "2018111111",
        //     workyear: "1年",
        //     age: "20",
        //     educationlevel: "本科"
        // },
        // {
        //     stuName: "张三",
        //     careerName: "前端开发工程师",
        //     stuId: "2018111111",
        //     workyear: "1年",
        //     age: "20",
        //     educationlevel: "本科"
        // },
        // {
        //     stuName: "张三",
        //     careerName: "前端开发工程师",
        //     stuId: "2018111111",
        //     workyear: "1年",
        //     age: "20",
        //     educationlevel: "本科"
        // },
        // {
        //     stuName: "张三",
        //     careerName: "前端开发工程师",
        //     stuId: "2018111111",
        //     workyear: "1年",
        //     age: "20",
        //     educationlevel: "本科"
        // },
        // {
        //     stuName: "张三",
        //     careerName: "前端开发工程师",
        //     stuId: "2018111111",
        //     workyear: "1年",
        //     age: "20",
        //     educationlevel: "本科"
        // }


      ],
      loading: false,
      tableData: [],
      currentPage: 1,
      pageSize: 6,
      totalItems: 0// 你的数据总条数

    }
  },
  mounted() {
    apiUser.getInfo().then((res) => {
      this.cid = res.data.cid[0]
      this.LoadEmployees()
      ElMessage.success("获取cid成功" + this.cid)
    }).catch((err) => {
      ElMessage.error(err)
    })


    this.loading = true;
    this.tableData = JSON.parse(JSON.stringify(this.employees))
    this.totalItems = this.tableData.length
    setTimeout(() => {
      this.loading = false
    }, 500)

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

        LoadEmployees() {
          ElMessage.success("查询人才库" + this.cid)
          api.get("/employees", {
                params: {
                  corporateId: this.cid,
                }
              }
          ).then((res) => {
            let data1 = []
            data1 = res
            data1.forEach(item=>{
              this.tableData.push(
                  {
                    stuName: item.stuName,
                    careerName: item.careerName,
                    stuId: item.stuID,
                    workyear: item.workyear,
                    age: item.age,
                    educationlevel: item.educationlevel
                  }
              )
            })
            console.log(this.employees)
            ElMessage.success('查询成功')
          }).catch((err) => {
            ElMessage.error(err)
          })
          // this.loading=false
        },
        handleCurrentChange(val) {
          this.loading = true
          this.currentPage = val;
        },
        handlePreview(row) {
          this.Preview = true
          this.Curjob = row
        },
        handleExitEdit() {
          this.editCareer = false
        }
      },
  computed: {
    currentPageData() {
      setTimeout(() => {
        this.loading = false
      }, 500);
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
        人才库
      </template>
      <template #content>
        投递简历的学生信息将在这里呈现
      </template>
    </PageHeader>
    <PageMain>
      <template #default>
        <el-row justify="space-between">
          <el-col v-for="employee in currentPageData" :key="employee.stuId">
            <el-card style="margin-bottom: 20px">
              <el-skeleton animated :loading="loading" :rows="2">
                <template #default>
                  <el-row justify="space-around">
                    <el-col :span="3">
                      <el-image style="width: 100px; height: 100px" src="http://vi.xjtu.edu.cn/img/a1-3jdxhred.png"
                                fit="cover"></el-image>
                    </el-col>
                    <!--                                   <el-col :span="12">-->
                    <!--                                     <el-tag type="success" style="margin-right: 10px">{{employee.careerName}}</el-tag>-->
                    <!--                                   </el-col>-->
                    <el-col :span="21">
                      <el-row justify="end">
                        <br><br>
                        <el-col :span="8">
                          <!--                                         <el-tag type="success" style="margin-right: 10px">{{employee.stuId}}</el-tag>-->
                          <el-text type="info" size="large" style="color: #1a1a1a;font-size: 20px">
                            <strong>
                              {{ employee.stuName }}
                            </strong>
                          </el-text>
                        </el-col>
                        <el-col :span="8">
                          <span></span>
                        </el-col>
                        <el-col :span="8">
                          <el-space spacer="|">
                            <el-text size="large" style="color:#000;">
                              <strong>
                                投递岗位
                              </strong>
                            </el-text>
                            <el-text type="warning" size="large">
                              <strong>
                                {{ employee.careerName }}
                              </strong>
                            </el-text>
                          </el-space>

                        </el-col>
                        <br><br>
                        <el-col :span="24">
                          <el-text type="info">
                            <strong>
                              学号：{{ employee.stuId }}
                            </strong>
                          </el-text>
                        </el-col>
                        <br><br>
                        <el-col :span="8">
                          <el-tag type="success" style="margin-right: 10px">{{ employee.age }}岁</el-tag>
                          <el-tag style="margin-right: 10px">{{ employee.educationlevel }}</el-tag>
                          <el-tag type="warning" style="margin-right: 10px">{{ employee.workyear }}</el-tag>
                        </el-col>
                        <el-col :span="8">
                          <span></span>
                          <!--                                         <el-tag type="success" style="margin-right: 10px">{{employee.stuId}}</el-tag>-->
                        </el-col>
                        <el-col :span="8">
                          <!--                                         <span></span>-->
                          <!--                                         <el-tag type="success" style="margin-right: 10px">{{employee.workyear}}</el-tag>-->
                          <el-button size="large" style="font-size: 16px" link type="primary"
                                     @click="handlePreview(employee)">
                            <SvgIcon name="fontisto:preview"/>
                            &nbsp;查看简历
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
