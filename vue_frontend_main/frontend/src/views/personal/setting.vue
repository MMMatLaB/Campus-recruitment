<route lang="yaml">
name: personalSetting
meta:
title: 个人设置
cache: false
</route>

<script setup lang="ts">
import {ElMessage} from 'element-plus'


defineOptions({
  name: 'PersonalSetting',
})

const router = useRouter()


</script>
<script lang="ts">
import apiUser from "../../api/modules/user";
import api from "../../api";
import {ElMessage, FormInstance, FormRules} from "element-plus";
import useUserStore from '@/store/modules/user'

import {valid} from "mockjs";

const userStore = useUserStore()

const formRef = ref<FormInstance>()
const form = ref({
  password: '',
  newpassword: '',
  checkpassword: '',
})
const rules = ref<FormRules>({
  password: [
    {required: true, message: '请输入原密码', trigger: 'blur'},
  ],
  newpassword: [
    {required: true, message: '请输入新密码', trigger: 'blur'},
    {min: 6, max: 18, trigger: 'blur', message: '密码长度为6到18位'},
  ],
  checkpassword: [
    {required: true, message: '请输入新密码', trigger: 'blur'},
    {
      validator: (rule: any, value: any, callback: any) => {
        if (value !== form.value.newpassword) {
          callback(new Error('请确认新密码'))
        } else {
          callback()
        }
      },
    },
  ],
})
export default {
  data() {
    return {
      roleID: '',
      user: {
        userID: '',
        major: '',
        company: '',
        tel: '',
        name: '',
        email: '',
        passwd: '',
      },
      form: {
        password: '',
        newpassword: '',
        checkpassword: '',
      }
    }

  },
  mounted() {
    this.getUserInfo()
    this.resetForm()

  },
  computed: {
    roleName() {
      switch (this.roleID) {
        case '0':
          return '学生用户';
        case '1':
          return '学校用户';
        case '2':
          return '企业用户';
        case '3':
          return '管理员';
        default:
          return '';
      }
    },
  },
  methods: {
    getUserInfo() {
      apiUser.getInfo().then(res => {
        this.roleID = res.data.roleid[0]
        this.user.userID = res.data.userid[0]
      }).then((res) => {
        api.get("/personalInfo", {
          params: {
            userName: this.user.userID
          }
        }).then((res) => {
          this.user.major = res.major0rCompany
          this.user.company = res.major0rCompany
          this.user.tel = res.tel
          this.user.name = res.name
          this.user.email = res.email
          this.user.passwd = res.passwd
          // this.form.password = res.passwd
          //console.log(user)
        })
        //console.log(user.userID)
      })

    },
    saveData() {
      const tel = this.user.tel;
      const email = this.user.email;
      //ElMessage.success("邮箱"+email)
      api.put("/personalInfo", {
        email: email,
        gender: '',
        id: this.user.userID,
        major0rCompany: this.user.company,
        name: this.user.name,
        passwd: this.user.passwd,
        tel: tel
      }).then(() => {
        ElMessage.success("更新成功")
      })
    },
    handleSubmit() {
      //ElMessage.success("dsafsadfasdf")
      // formRef.value && formRef.value.validate((valid) => {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          console.log(
            this.user.passwd
          )
          this.form.password = document.getElementById('formPasswd').value
          console.log(this.form.password)
          if (this.form.password === this.user.passwd) {
            api.put("/personalInfo", {
              email: this.user.email,
              gender: '',
              id: this.user.userID,
              major0rCompany: this.user.company,
              name: this.user.name,
              passwd: document.getElementById('formNewpasswd').value,
              tel: this.user.tel
            })
            this.resetForm()
            ElMessage({
              type: 'success',
              message: '密码修改成功，请重新登录'
            })

            setTimeout(() => {
              userStore.logout()
            }, 2000)

          } else {
            ElMessage({
              type: 'error',
              message: '原密码错误',
            })
          }
        }
      })
    },
    resetForm() {
      this.form.password = '';
      this.form.newpassword = '';
      this.form.checkpassword = '';
    }
  }

}

</script>
<template>
  <div>
    <PageMain>
      <ElTabs tab-position="left" style="height: 600px;">
        <ElTabPane label="基本设置" class="basic">
          <h2>基本设置</h2>
          <ElRow :gutter="20">
            <ElCol :span="16">
              <ElForm :model="form" label-width="120px" label-suffix="：">
                <ElFormItem label="账号">
                  <ElInput v-model="user.userID" :value="user.userID" disabled/>
                </ElFormItem>
                <ElFormItem label="真实姓名">
                  <ElInput v-model="user.name" :value="user.name" disabled/>
                </ElFormItem>
                <ElFormItem label="角色">
                  <ElInput :value="roleName" disabled/>
                </ElFormItem>
                <ElFormItem v-if="roleID === '0'" label="专业">
                  <ElInput v-model="user.major" :value="user.major" disabled/>
                </ElFormItem>
                <ElFormItem v-if="roleID === '2'" label="公司">
                  <ElInput v-model="user.company" :value="user.company" disabled/>
                </ElFormItem>
                <ElFormItem label="手机号">
                  <ElInput v-model="user.tel" :value="user.tel"/>
                </ElFormItem>
                <ElFormItem label="邮箱">
                  <ElInput v-model="user.email" :value="user.email"/>
                </ElFormItem>
                <ElFormItem>
                  <ElButton type="primary" @click="saveData">
                    保存
                  </ElButton>
                </ElFormItem>
              </ElForm>
            </ElCol>

          </ElRow>
        </ElTabPane>
        <ElTabPane label="修改密码" class="security">
          <h2>修改密码</h2>
<!--          <el-text type="danger" size="small" style="font-size: 15px">-->
<!--              提交后需重新登录-->
<!--          </el-text>-->
          <div class="setting-list">
            <ElRow>
              <ElCol :md="24" :lg="12">
                <ElForm ref="formRef" :model="form" :rules="rules" label-width="120px">
                  <ElFormItem label="原密码" prop="password">
                    <input type="text" style="display:none" />
                    <ElInput v-model="form.password" id="formPasswd" type="password" placeholder="请输入原密码" show-password autocomplete="off"/>
                  </ElFormItem>
                  <ElFormItem label="新密码" prop="newpassword">
                    <input type="text" style="display:none" />
                    <ElInput v-model="form.newpassword" type="password" placeholder="请输入新密码" show-password autocomplete="off"/>
                  </ElFormItem>
                  <ElFormItem label="确认新密码" prop="checkpassword">
                    <input type="text" style="display:none" />
                    <ElInput v-model="form.checkpassword" id="formNewpasswd" type="password" placeholder="请确认新密码" show-password autocomplete="off"/>
                  </ElFormItem>
                </ElForm>
              </ElCol>
            </ElRow>
            <!--              <FixedActionBar>-->
            <div class="center-container">
              <el-tooltip
                class="box-item"
                effect="dark"
                content="修改密码后需要重新登录"
                placement="bottom"
                raw-content="true"
              >
                <ElButton type="primary" @click="handleSubmit">
                  提交
                </ElButton>
              </el-tooltip>

            </div>
            <!--              </FixedActionBar>-->
          </div>
        </ElTabPane>
      </ElTabs>
    </PageMain>
  </div>
</template>

<style lang="scss" scoped>
:deep(.el-tabs) {
  .el-tabs__header .el-tabs__nav {
    .el-tabs__active-bar {
      z-index: 0;
      width: 100%;
      background-color: var(--el-color-primary-light-9);
      border-right: 2px solid var(--el-color-primary);
      transition: transform 0.3s, background-color 0.3s, var(--el-transition-border);
    }

    .el-tabs__item {
      text-align: left;
      padding-right: 100px;
    }
  }

  .el-tab-pane {
    padding: 0 20px 0 30px;
  }
}

h2 {
  margin: 0;
  margin-bottom: 0px;
  font-weight: normal;
}

.basic {
  :deep(.headimg-upload) {
    text-align: center;

    .el-upload-dragger {
      border-radius: 50%;
    }
  }
}

.center-container {
  display: flex;
  justify-content: flex-start;
  margin-left: 25%;
}

.security {
  .setting-list {
    .item {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 10px 0;
      border-bottom: 1px solid var(--el-border-color-lighter);
      transition: var(--el-transition-border);

      .content {
        .title {
          margin-bottom: 5px;
          color: var(--el-text-color-primary);
          transition: var(--el-transition-color);
        }

        .desc {
          font-size: 14px;
          color: var(--el-text-color-secondary);
          transition: var(--el-transition-color);
        }
      }

      &:last-child {
        border-bottom: 0;
      }
    }
  }
}
</style>
