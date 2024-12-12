<script lang="ts">
export default {
  data() {
    return {
      // loadingShow: true,
      Asrc: 'http://localhost:3000/designer',
    }
  },
  mounted() {
  },
  methods:{

  }
};
</script>

<template>
<div>
  <PageHeader title="问卷设计" >
   <template #content>
     问卷设计让您所见即所得，轻松设计问卷，推荐在模板的基础上进行问卷设计
   </template>
  </PageHeader>
  <PageMain>
    <!--  <template #title> -->
    <!--    密码：p6ZrLQXj -->
    <!--    </template> -->
<!--    <iframe src="http://localhost:3000" width="100%" height="800" allowfullscreen  frameborder="0"/>-->
    <iframe name="iframeMap"
            width="100%" height="800" allowfullscreen
            v-bind:src="Asrc"
            frameborder="0"
            scrolling="no"
            ref="Iframe"
    ></iframe>
  </PageMain>
</div>
</template>

<style scoped lang="scss">

</style>
