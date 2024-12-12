<script setup>

import axios from "axios"


// const currentDate = new Date();
// const formattedDate = format(currentDate, 'yyyy-MM-dd HH:mm:ss');

</script>
<script>
// import { format } from 'date-fns';
import {ElMessage} from "element-plus";
// import axios from "axios";
// import api from "@/api/index.js";
import {formatDate} from "@vueuse/core";
import {computed} from "vue";
import axios from "axios";
import img from '../../../assets/images/no1.png'
import api from "@/api";
export default {

  data() {
    return {
      imgUrl:img,
      reportGroups: [],
      reports:[],
      Preview: false,
      newCareer: false,
      editCareer: false,
      loading: false,
      tableData: [],
      reportExisted:true,
      currentPage: 1,
      pageSize: 6,
      totalItems: 0// 你的数据总条数

    }
  },
  mounted() {
    // this.LoadJobs()

    this.LoadReports()
  },
  methods:
    {
        handleFocus(report){
            this.$router.push({path:'/graduateStu/preview/'+report.url})


    },
      LoadReports() {
        // this.loading=true
        api.get("/stu/employmenReports", {
            params: {
              title: ''
            }
          }
        ).then((res) => {

          ElMessage.success('查询成功'+res.length.toString())
          this.reports = res
          if(res.length>0)
          {
            this.reportExisted=false
          }
          //console.log(this.reports)
          this.reports.forEach((item, index) => {

            const groupIndex = Math.floor(index % 3);
            console.log(groupIndex)
            const yearMatch = item.title.match(/\d{4}/);
            const year = yearMatch ? parseInt(yearMatch[0]) : null;


            if (!this.reportGroups[groupIndex]) {
              this.reportGroups[groupIndex] = [];
            }

            this.reportGroups[groupIndex].push({
              id: item.reportID,
              year: year,
              title: item.title,
              time: item.time,
                url:item.url
            });
          });
          console.log(this.reportGroups[0])

        }).catch((err) => {
          ElMessage.error(err)
        })
        //console.log(this.reports)

      },

    },
  computed: {

  },
}

</script>
<template>
  <div>
    <PageHeader>
      <template #title>
        年度就业质量报告
      </template>
      <template #content>
        往年已发布的毕业生就业质量报告将在这里呈现
      </template>
    </PageHeader>
    <PageMain>
      <template #default>
        <el-empty style="height: 500px" description="还没有发布的就业质量报告，再等等吧！" v-if="reportExisted">
<!--          <el-button type="primary" @click="updateResume" v-if="resumeExisted">创建简历</el-button>-->
        </el-empty>
        <el-row :gutter="60" style="margin-left: 4%;">
          <el-col v-for="(group, index) in reportGroups" :key="index" :span="7">
            <el-card shadow="hover"
                     v-for="report in group" :key="report.id" :body-style="{ padding: '5px' }" style="margin-bottom: 25px;border: 2px solid #ccc;">
              <img  @click="handleFocus(report)"
                :src="imgUrl"
                class="image"
              />
              <div style="padding: 14px">
                <span><h2>{{report.title}}</h2></span>
<!--                  <el-text type="danger" size="large" style="font-size: 17px">-->
<!--                    <h3>  {{ report.year }}</h3>-->

<!--&lt;!&ndash;                    </strong>&ndash;&gt;-->
<!--                  </el-text>-->
                <div>
                  <el-row>
                    <el-col :span="4">
                      <el-tag type="primary" >
                        <strong>
                          {{ report.year }}年
                        </strong>
                      </el-tag>
                    </el-col>
                    <el-col :span="4">
                   <span></span>
                    </el-col>
                    <el-col :span="16">
                      <el-tag type="success">
                        <strong>
                          发布时间：{{ report.time.split(' ')[0] }}
                        </strong>
                      </el-tag>
                    </el-col>

                  </el-row>

                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
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
.image {
  width: 100%;
  height: auto;
    cursor:pointer
}
.bottom {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.button {
  float: right;
}
</style>
