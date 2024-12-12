<script setup lang="ts">

</script>
<script>
export default {
  // name: 'PreviewView',
  data() {
    return {
      loading:false,
      file: '',
      url:'',
    }
  },
  mounted() {
    this.init()
  },
  methods:{
    init() {
      this.url = 'http://localhost:3000/preview/'+this.$route.params.id;
      this.loading = true;
    }
  }
}
</script>
<template>
<div>
  <PageHeader title="问卷预览" />
  <PageMain>
    <template #title>
      <el-button @click="this.$router.push({path: '/questionnaire_management/info'})">返回</el-button>
    </template>
    <template #default>
      <iframe v-if="loading" :src="url" frameborder="0" width="100%" height="600px"></iframe>
    </template>
<!--    {{url}}-->

  </PageMain>
</div>
</template>

<style scoped lang="scss">

</style>
