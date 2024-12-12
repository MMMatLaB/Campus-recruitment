<template>
<div>
  <PageHeader>
    <template #title>
      Markdown
    </template>
  </PageHeader>
  <PageMain>
    <template #default>
      <div id="vditor" ></div>
    </template>

  </PageMain>
</div>
</template>
<script setup>
import { defineProps, ref, onMounted } from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
const contentEditor = ref("");
onMounted(() => {
  contentEditor.value = new Vditor("vditor", {
    height: 360,
    toolbarConfig: {
      pin: true,
    },
    cache: {
      enable: false,
    },
    preview: {
      markdown: {
        toc: true,
      },
      editor: true,
    },
    after: () => {
      contentEditor.value.setValue("hello,Vditor+Vue!");
    },
  });
});
</script>
<style scoped>
a {
  color: #42b983;
}
</style>

