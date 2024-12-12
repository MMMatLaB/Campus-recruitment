<script lang="ts">
import {ElMessage} from "element-plus";
import api from "@/api";
import {FormInstance, FormRules} from 'element-plus'


export default {
  data() {
    return {
      loading: false,
      currentPage: 1,
      pageSize: 10,
      totalItems: 0,
      tableData: [],
      newRelation: {
        mid: '',
        did: '',
      },
      dialogVisible: false,

      majors: [],
      directions: [],
      searchParams: {
        major: '',
        direction: ''
      },
    }
  },
  mounted() {
    this.loadMajor()
    this.loadDirection()
    this.loadDefultRelations()
  },
  methods: {
    loadMajor() {
      api.get("/majors", {
        params: {
          major: ''
        }
      }).then((res) => {
        let data1=[]
        data1=res
        data1.forEach(item=>{
          this.majors.push(
            {mid:item.mid.toString(),majorName:item.majorName}
          )
        })
        //this.majors = res
        //console.log(this.majors)
      })
    },
    loadDirection() {
      api.get("/directions", {
        params: {
          major: '',
          direction: ''
        }
      }).then((res) => {
        let data1=[]
        data1=res
        data1.forEach(item=>{
          this.directions.push(
            {directid:item.directid.toString(),directname:item.directname}
          )
        })
        //.log(this.directions)
      })
    },
    loadDefultRelations() {
      api.get("/relations", {
        params: {
          major: '',
          direction: ''
        }
      }).then((res) => {
        this.tableData = res
        this.totalItems = this.tableData.length
        //console.log(this.tableData)
      })
    },
    loadRelations() {
      api.get("/relations", {
        params: {
          major: this.searchParams.major,
          direction: this.searchParams.direction
        }
      }).then((res) => {
        this.tableData = res
        this.totalItems = this.tableData.length
        //console.log(this.tableData)
      })
    },

    handleDelete(row) {
      this.$confirm('此操作将永久删除该关系, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        api.delete('/relation/major/' + row.majormid + '/direction/' + row.directdirectID).then(() => {
          ElMessage.success("删除成功")
          this.loadDefultRelations()
        }).catch((err) => {
          ElMessage.error(err)
        })
      })
      //ElMessage.success("删除"+row.name)
    },
    closeDialog() {
      this.dialogVisible = false
    },
    addRelation() {
      if (this.newRelation.mid.length > 0 && this.newRelation.did.length > 0) {
        this.loading = true;
        api.post('/relation', {
          directdirectID: this.newRelation.did,
          majormid: this.newRelation.mid
        }).then((res) => {
          ElMessage.success('添加成功');
          this.loadDefultRelations();
          this.closeDialog();
        }).catch((err) => {
          ElMessage.error(err);
        });
      }else {
        ElMessage.error("请选择专业和方向")
      }
    },
    handleCurrentChange(val) {
      this.loading = true
      this.currentPage = val;
    },
    clear() {
      this.searchParams.major = '';
      this.searchParams.direction = '';
      this.loadDefultRelations()
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
  }
}
</script>
<template>
  <div>
    <PageHeader>
      <template #title>
        关系配对
      </template>
    </PageHeader>
    <PageMain>
      <template #title>

        <el-form :inline="true" :model="searchParams" style="width: 1500px;">
          <el-form-item label="专业" prop="majors">
            <el-select v-model="searchParams.major" placeholder="请选择专业">
              <el-option v-for="major in majors" :key="major" :label="major.majorName" :value="major.mid">
                {{ major.majorName }}
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="方向" prop="directions">
            <el-select v-model="searchParams.direction" placeholder="请选择方向">
              <el-option v-for="direction in directions" :key="direction" :label="direction.directname"
                         :value="direction.directid">
                {{ direction.directname }}
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button @click="loadRelations">
              查询
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button @click="clear">
              清除
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="dialogVisible=true">
              <SvgIcon name="gridicons:add-outline"/>
              &nbsp;
              新增就业方向
            </el-button>
          </el-form-item>
        </el-form>
      </template>
      <el-table :data="currentPageData" style="width: 100%" v-loading="loading" border>
        <el-table-column type="index" label="编号" width="180" align="center"></el-table-column>
        <el-table-column prop="majorName" label="专业" align="center"></el-table-column>
        <el-table-column prop="directName" label="就业方向" align="center"></el-table-column>
        <!--        <el-table-column prop="telephone" label="联系电话" width="180"></el-table-column>-->
        <!--        <el-table-column prop="status" label="状态" width="180"></el-table-column>-->
        <el-table-column label="操作" width="180" align="center">
          <template #default="scope">

            <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
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
      <HDialog v-model="dialogVisible" title="新增关系配对">
        <template #default>
          <div style="width: 50%">
            <el-form v-model="newRelation" >
              <el-form-item label="专业名称" prop="mid">
                <!--                <el-input v-model="newDirection.name" />-->
                <el-select v-model="newRelation.mid" placeholder="请选择专业">
                  <el-option v-for="major in majors" :key="major" :label="major.majorName" :value="major.mid">
                    {{ major.majorName }}
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="就业方向" prop="did">
                <el-select v-model="newRelation.did" placeholder="请选择就业方向">
                  <el-option v-for="direction in directions" :key="direction" :label="direction.directname"
                             :value="direction.directid">
                    {{ direction.directname }}
                  </el-option>
                </el-select>
              </el-form-item>
            </el-form>
          </div>
        </template>
        <template #footer>
          <el-button @click="closeDialog">取消</el-button>
          <el-button type="primary" @click="addRelation">
            确定
          </el-button>
        </template>
      </HDialog>
    </PageMain>
  </div>
</template>

<style scoped lang="scss">

</style>
