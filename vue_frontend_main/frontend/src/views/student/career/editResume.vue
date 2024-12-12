<script setup lang="ts">

</script>
<script lang="ts">
import {ElMessage} from "element-plus";
import apiUser from "../../../api/modules/user";
import api from "@/api";
import router from "@/router";
import type { FormInstance, FormRules } from 'element-plus'
const ruleFormRef = ref<FormInstance>()

export default {
  data(){
    // var checkAge
    return{
      editResume:{
        name:'',
        age:'',
        educationlevel:'',
        workplace:'',
        idealcareer:'',
        idealdomain:"",
        idealcity:'',
        idealsalary:'',
        workyear:"",
        rtel:'',
        description:''
      },
      resume:{
        //TODO LoadResume
      },
      resumeExisted: false,
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
      uid:'',
      rules:{
        age:[
          { validator: this.checkAge, trigger: 'blur' }
        ],
        // rtel:[{
        //     validator: this.checkTel, trigger: 'blur'}
        // ]
      }
    };
  },
  mounted(){
    this.loadUid()
  },
  methods: {
  // checkTel(rule: any, value: any, callback: any){
  //   // console.log(this.editResume.rtel)
  //   const isNumericString = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/.test(this.editResume.rtel);
  //   value = this.editResume.rtel
  //   // const isNumericString = /^\d+$/.test(value);
  //   console.log(parseInt(value))
  //   console.log(isNumericString)
  //   setTimeout(() => {
  //     if (!isNumericString) {
  //       callback(new Error('请输入正确手机号'));
  //     } else {
  //         callback();
  //       }
  //   }, 100);
  // },
    checkAge(rule: any, value: any, callback: any)
{
  value = this.editResume.age
  const isNumericString = /^\d+$/.test(value);
  // console.log(parseInt(value))
  // console.log(isNumericString)
  setTimeout(() => {
    if (!isNumericString) {
      callback(new Error('请输入数字值'));
    } else {
      if ((parseInt(value) < 15 || parseInt(value) > 80)) {
        callback(new Error('年龄异常'));
      } else {
        callback();
      }
    }
  }, 100);
},
    loadUid(){
      apiUser.getInfo().then(res=>{
        this.uid=res.data.userid[0]
        // ElMessage.success('获取uid成功'+this.uid)
        this.LoadResume()
      }).catch(err=>{
        ElMessage.error(err)
      })
    },
    LoadResume(){
      api.get('/resume',{
        params:{
          userName:this.uid
        }
      }).then(res=>{
        if(res){
          this.resume= res
          this.resumeExisted = true
          this.editResume =  res as any
          ElMessage.success('获取简历成功')
        }

      }).catch(err=>{
        ElMessage.error(err)
      })
    },
    SubmitResume(){
      // console.log(this.resumeExisted)
      if(this.resumeExisted)
      {//更新
        ElMessage.success()
        // api.put('/resume',this.editResume).then(res=>{
        //   ElMessage.success('修改成功')
        // }).catch(err=>{
        //   ElMessage.error(err)
        // })
      }else{//新建
        ElMessage.error()
        api.post('/resume',{
          userID:this.uid,
          age:this.editResume.age,
          educationlevel:this.editResume.educationlevel,
          workplace:this.editResume.idealcity,
          idealcareer:this.editResume.idealcareer,
          idealdomain:this.editResume.idealdomain,
          idealcity:this.editResume.workplace.toString().replace(',',"/"),
          idealsalary:this.editResume.idealsalary,
          workyear:this.editResume.workyear,
          tel:this.editResume.rtel,
          description:this.editResume.description
        }).then(res=>{
          ElMessage.success('简历创建成功')
        }).catch(err=>{
          ElMessage.error(err)
        })
      }

    },
    goback(){
      router.push({path:'/'})
    }
}
}
</script>
<template>
<div>
  <PageHeader title="编辑简历">

  </PageHeader>
  <PageMain>
    <template #title>
      <el-row>
        <el-col :span="2">
          <el-button @click="goback"> 返回</el-button>
        </el-col>
<!--        <el-col :span="2" >-->
<!--        -->
<!--        </el-col>-->
      </el-row>
    </template>
    <template #default>
      <el-scrollbar style="height: 700px; width: 100%">
        <el-form ref="ruleFormRef" :rules="rules">
          <el-row >
            <el-text>
              <h3>基本信息</h3>
            </el-text>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="10">
              <el-form-item label="姓名">
                <el-input v-model="editResume.rname" style="width: 215px"  class="centered-input"></el-input>
              </el-form-item>
            </el-col>
            <!--            <el-divider direction="horizontal" border-style="hidden"></el-divider>-->
            <el-col :span="10">
              <el-form-item label="年龄" prop="age">
                <el-input v-model="editResume.age" controls-position="right" style="width: 215px"  ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="10">
              <el-form-item label="电话"  >
                <el-input v-model="editResume.rtel" style="width: 215px"  class="centered-input"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="10">
              <el-form-item label="学历" style="text-align: center;">
                <el-select v-model="editResume.educationlevel" placeholder="请选择"  style="text-align: center;" fit-input-width  class="centered-input">
                  <el-option
                      v-for="item in educationRequirements"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="经验">
                <el-select v-model="editResume.workyear" placeholder="请选择">
                  <el-option
                      v-for="item in workExperiences"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-text>
              <h3>求职意向</h3>
            </el-text>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="10">
              <el-form-item label="城市">
                <PcasCascader v-model="editResume.workplace" type="pc" format="name" />
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="行业">
                <el-select v-model="editResume.idealdomain" placeholder="请选择">
                  <el-option
                      v-for="item in domains"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="10">
              <el-form-item label="职位">
                <el-input v-model="editResume.idealcareer" style="width: 215px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="薪资">
                <el-select v-model="editResume.idealsalary" placeholder="请选择">
                  <el-option
                      v-for="item in salaries"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-text>
              <h3>个人简介</h3>
            </el-text>
          </el-row>
          <el-form-item>
            <el-input  type="textarea" v-model="editResume.description" :rows="8"
                       maxlength="500"
                       show-word-limit style="width: 800px;">

            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" plain round >AI润色</el-button>
          </el-form-item>
        </el-form>
      </el-scrollbar>
      <FixedActionBar>
        <el-button type="primary" @click="SubmitResume" size="large">保存</el-button>
      </FixedActionBar>
    </template>
  </PageMain>
</div>
</template>

<style scoped lang="scss">
.centered-input {
  text-align: center;
}
</style>
