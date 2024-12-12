<script lang="ts">
import api from '@/api'
import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      loading:false,
      currentPage: 1,
      pageSize: 10,
      totalItems: 0,
      tableData: [],
      newRoleDialog: false,
      EditRoleDialog: false,
      addRoleForm: {
        rolename:'',
        status: 0,
      },
      editRole: {
        rolename:'',
        status: 0,
        idrole: '',
      },

      // roles:[]
    }
  },
  methods: {
    handleDelete(row){
      if(row.idrole==0||row.idrole==1||row.idrole==2||row.idrole==3)
      {
        ElMessage.error("该用户不可删除")
        return
      }
      this.loading = true
      api.delete('/role/'+row.idrole
      ).then((res)=>{
        ElMessage.success("删除成功")
        this.LoadData()
      }).catch(error => {
        ElMessage.error("删除失败")
      });
      // console.log(row)
      // ElMessage.error("Delete")
    },
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
    LoadData()
    {
      // ElMessage.error("Load")
      api.get('/roles'
      ).then((res)=>{
        // ElMessage.success("loaded")
        this.tableData = res as any
        this.totalItems = this.tableData.length
        this.loading=false
        // ElMessage.success(res)
      }).catch(error => {

      });
    },
    exitNewRole(){
      this.EditRoleDialog = false
      this.editRole = {
        rolename: '',
        status: 1,
      }
    },
    handleEdit(row) {
      // ElMessage.error("Edit")
      this.EditRoleDialog = true
      this.editRole = {
        idrole: row.idrole,
        rolename: row.rolename,
        status: row.status,
      }
      // this.$router.push({path: '/admin/role/edit', query: {id: row.id}})
    },
    handleCurrentChange(val) {
      this.loading=true
      this.currentPage = val;
    },
    handleNewRole() {
      this.newRoleDialog = true
    },
    SubmitNewRole() {
      this.loading = true
      api.post('/role', {
        rolename: this.addRoleForm.rolename,
        status: this.addRoleForm.status,
        createtime: this.formatDate(new Date())
      }
      ).then((res) => {
        ElMessage.success("添加成功")
        this.newRoleDialog = false
        this.LoadData()
      }).catch(error => {
        ElMessage.error("添加失败")
      });
    },
    exitEditUser() {
      this.EditRoleDialog = false
      this.editRole = {
        idrole: '',
        rolename: '',
        status: 1,
      }
    },SubmitEditUser(){
      this.loading = true
      api.put('/role/'+this.editRole.idrole, {
        idrole:this.editRole.idrole,
        rolename: this.editRole.rolename,
        status: this.editRole.status,
        createtime: this.formatDate(new Date())
      }
      ).then((res) => {
        ElMessage.success("修改成功")
        this.EditRoleDialog = false
        this.LoadData()
      }).catch(error => {
        ElMessage.error("修改失败")
      });
    },
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
  mounted() {
      this.LoadData()
  }
}
</script>

<template>
  <div>
    <PageHeader title="角色管理" content="" />
    <PageMain>
      <template #title>
        <el-form :inline="true"  style="width: 1500px;">
          <el-form-item>
            <el-button type="primary" @click="handleNewRole">
              <SvgIcon name="gridicons:add-outline"/>&nbsp;
              新增角色</el-button>
          </el-form-item>
        </el-form>
      </template>
      <el-table :data="currentPageData" border style="width: 100%"  v-loading="loading">
        <el-table-column type="index" label="序号" width="80" align="center" />
        <el-table-column prop="rolename" label="名称" align="center"/>
        <el-table-column prop="createtime" label="更新时间" align="center" />
        <el-table-column label="状态" align="center">
          <template #default="scope">
            <el-tag :type="scope.row.status === 0 ? 'danger' : 'success'">
              {{ scope.row.status === 0 ? '禁用' : '启用' }}
            </el-tag>
          </template>
        </el-table-column>
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
    <HDialog v-model="newRoleDialog" title="新增角色">
      <template #default>
        <div style="width: 50%">
            <el-form-item label="名称" prop="name">
              <el-input v-model="addRoleForm.rolename" />
            </el-form-item>
            <el-form-item
                prop="status"
                label="是否启用"
                width="180">
              <!--                  <template #default="scope">-->
              <el-switch
                  v-model="addRoleForm.status"
                  active-color="#409eff"
                  inactive-color="gray"
                  active-value="1"
                  inactive-value="0"
                  inline-prompt
                  active-text="是"
                  inactive-text="否">
              </el-switch>
              <!--                  </template>-->
            </el-form-item>
            <!--          <el-form-item>-->
            <!--              {{addUserForm.status}}-->
            <!--          </el-form-item>-->
        </div>
      </template>
      <template #footer>
        <el-button @click="exitNewRole">取消</el-button>
        <el-button @click="SubmitNewRole" type="primary" >
          确定
        </el-button>
      </template>
    </HDialog>
    <HDialog v-model="EditRoleDialog" title="修改角色">
      <template #default>
        <div style="width: 50%">
          <el-form v-model="editRole">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="editRole.rolename" />
            </el-form-item>
            <el-form-item
                prop="status"
                label="是否启用"
                width="180">
              <!--                  <template #default="scope">-->
              <el-switch
                  v-model="editRole.status"
                  active-color="#409eff"
                  inactive-color="gray"
                  :active-value="1"
                  :inactive-value="0"
                  inline-prompt
                  active-text="是"
                  inactive-text="否">
              </el-switch>
              <!--                  </template>-->
            </el-form-item>
          </el-form>
        </div>
      </template>
      <template #footer>
        <el-button @click="exitEditUser">取消</el-button>
        <el-button type="primary" @click="SubmitEditUser">
          确定
        </el-button>
      </template>
    </HDialog>
  </div>
</template>

<style scoped lang="scss">

</style>
