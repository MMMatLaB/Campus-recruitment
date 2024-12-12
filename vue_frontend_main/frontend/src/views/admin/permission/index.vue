<script lang="ts">
export default {
  data() {
    return {
      permissions: [
        { id: 1, name: '权限1' },
        { id: 2, name: '权限2' },
        // 初始权限数据
      ],
      dialogVisible: false,
      newPermission: {
        name: '',
      },
      rules: {
        name: [
          { required: true, message: '请输入权限名称', trigger: 'blur' },
        ],
      },
    }
  },
  methods: {
    editPermission(permission) {
      // 编辑权限逻辑
    },
    deletePermission(permissionId) {
      // 删除权限逻辑
    },
    closeDialog() {
      this.dialogVisible = false
      this.$refs.newPermissionForm.resetFields()
    },
    addPermission() {
      this.$refs.newPermissionForm.validate((valid) => {
        if (valid) {
          // 添加权限逻辑
          this.permissions.push({
            id: this.permissions.length + 1,
            name: this.newPermission.name,
          })
          this.closeDialog()
        }
      })
    },
  },
}
</script>

<template>
  <div>
    <PageHeader>
      <template #title>
        权限管理
      </template>
      <template #action>
        <el-button type="primary" icon="el-icon-plus">
          权限
        </el-button>
      </template>
    </PageHeader>
    <PageMain>
      <template>
        <div>
          <!-- 权限列表 -->
          <el-table :data="permissions" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80" />
            <el-table-column prop="name" label="权限名称" />
            <el-table-column label="操作">
              <template #default="scope">
                <el-button type="text" size="small" @click="editPermission(scope.row)">
                  编辑
                </el-button>
                <el-button type="text" size="small" @click="deletePermission(scope.row.id)">
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>

          <!-- 添加权限的对话框 -->
          <el-dialog v-model:visible="dialogVisible" title="添加权限" @close="closeDialog">
            <el-form ref="newPermissionForm" :model="newPermission" :rules="rules" label-width="80px">
              <el-form-item label="权限名称" prop="name">
                <el-input v-model="newPermission.name" />
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="closeDialog">
                取消
              </el-button>
              <el-button type="primary" @click="addPermission">
                确定
              </el-button>
            </div>
          </el-dialog>
        </div>
      </template>
    </PageMain>
  </div>
</template>

<style scoped lang="scss">

</style>
