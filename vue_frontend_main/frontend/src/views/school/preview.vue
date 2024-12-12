<script setup>
// import {defineProps, ref, onMounted} from "vue";
// import Vditor from "vditor";
// import "vditor/dist/index.css";
// import api from "@/api";
// import {ElMessage} from "element-plus";
// const contentEditor = ref("");
// onMounted(() => {
//   contentEditor.value = new Vditor("vditor", {
//     height: 360,
//     toolbarConfig: {
//       pin: true,
//     },
//     cache: {
//       enable: false,
//     },
//     preview: {
//       markdown: {
//         toc: true,
//       },
//       editor: true,
//     },
//     after: () => {
//       contentEditor.value.setValue("hello,Vditor+Vue!");
//     },
//   });
// });
</script>

<template>
  <div>
    <PageHeader title="就业质量报告"/>
    <PageMain>
      <template #title>
        <el-button @click="this.$router.push({path: '/graduate/info'})">返回</el-button>
<!--        {{this.md}}-->
      </template>
      <template #default>
        <div v-loading="loading">
          <markdown-preview class="markdown-warpper">
            {{this.md}}
          </markdown-preview>

        </div>
<!--        {{this.$route.params.id}}-->

<!--        <npm i @uivjs/vue-markdown-preview :source="md" />-->
<!--        <div id="vditor"></div>-->
      </template>

    </PageMain>
  </div>
</template>
<script>
import { defineComponent } from 'vue';
import MarkdownPreview from '@uivjs/vue-markdown-preview';
import '@uivjs/vue-markdown-preview/markdown.css';
import {ElMessage} from "element-plus";
import api from "@/api";
import Vditor from "vditor";
export default {
  components: {
    MarkdownPreview
  },
  name: "report",
  data() {
    return {
      // formJson:{}
      md:"",
      loading:true,
      couter:0
    }
  },
  methods:{
  loadMD(){
    api.get('http://127.0.0.1:7000/hdfs/report',{
      params:{
        url: this.$route.params.id
      }
    }).then((res)=>{
      if(res.code==200){
        this.loading=false
        this.md=res.data
        ElMessage.success("报告加载成功")
      }else {
        // ElMessage.error("报告加载失败")
        setTimeout(()=>{
          this.couter = this.couter+1
          if(this.couter>3)
          {
            ElMessage.error("报告加载失败,请检查网络")
            this.couter=0
            return
          }

          this.loadMD()
        },200)
        // this.loadMD()
      }
    }).catch((err)=>{
          ElMessage.error("报告加载失败")
        }
    )
  }},
  mounted() {
    this.loadMD()
  },
}
</script>
<style scoped lang="scss">

</style>
