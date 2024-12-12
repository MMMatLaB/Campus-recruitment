<route lang="yaml">
name: home
meta:
  title: 主页
  icon: ant-design:home-twotone
</route>

<script setup lang="ts">
const versionType = ref('basic')
function handelVersionTypeChange(val?: string | number) {
  if (val === 'pro') {
    location.href = location.href.replace('basic-example', 'pro-example')
  }
}

const fantasticStartkitInfo = ref({
  feature: [
    '支持 TypeScript',
    '默认集成 vue-router 和 pinia',
    '支持基于文件系统的路由',
    '全局组件自动引入',
    '全局 SCSS 资源引入',
    '支持 Unocss',
    '支持 SVG 文件图标、Iconify 图标、UnoCSS 图标',
    '支持 mock 数据，可脱离后端束缚独立开发',
    '支持 gzip / brotli 优化项目体积，提高加载速度',
    '结合 IDE 插件、ESlint 、stylelint 、Git 钩子，轻松实现团队代码规范',
  ],
})

const fantasticAdminInfo = ref({
  imageVisible: false,
  index: 0,
  data: [
    'https://picdm.sunbangyan.cn/2024/01/10/ef8bc1ccb0fbdeee2dd48a3c94ba1c43.jpeg',
    'https://picdm.sunbangyan.cn/2024/01/10/e028a01f3f7e0bff678c40ecc3ebc5b1.jpeg',
    'https://picss.sunbangyan.cn/2024/01/10/e2b494c6e5065d848c88005917423ff4.jpeg',
    'https://picst.sunbangyan.cn/2024/01/10/4473fdeb3394007586f08c8b40ed2b18.jpeg',


  ],
})
const oneStepStudentInfo = ref({
  feature: [
    '探索就业信息的多维度盛宴，解锁职场无限可能',
    '揭秘各年度就业质量报告，洞悉就业趋势',
    ' 一键送达求职简历，开启职业征程',
    '智能推荐需求岗位，为你量身定制职业之路',
  ],
})
const oneStepSchoolInfo = ref({
  feature: [
    '毕业生数据批量导入, 激活职业生涯开端',
    '自动可视化分析,揭示就业趋势的神秘面纱',
    '年度就业质量报告一键生成，洞悉毕业生去向',
    '调研问卷一键发布，点燃校企沟通的火花',
  ],
})

const oneStepHRInfo = ref({
  feature: [
    '点燃校园招聘的火花，便捷发布各类岗位',
    '后备人才源源不断，打造人才库的无尽宝藏',
    '一键获取简历信息，掌握求职者的无限潜力',
  ],
})

const oneStepAdminInfo = ref({
  imageVisible: false,
  index: 0,
  data: [
    'https://one-step-admin.gitee.io/preview1.png',
    'https://one-step-admin.gitee.io/preview2.png',
    'https://one-step-admin.gitee.io/preview3.png',
    'https://one-step-admin.gitee.io/preview4.png',
    'https://one-step-admin.gitee.io/preview5.png',
    'https://one-step-admin.gitee.io/preview6.png',
  ],

})

function open(url: string) {
  window.open(url, '_blank')
}
</script>

<template>
  <div>
  <PageHeader>
  </PageHeader>
    <PageMain class="rounded-lg text-white" style="background: linear-gradient(100deg, rgb(23,67,112), rgb(231,242,248));font-family: Arial, sans-serif; font-size: 20px; font-weight: bold;">
      <span class="custom-font">欢迎来到职业能力大数据服务平台!</span>
    </PageMain>
    <PageMain>
      <ElCarousel  trigger="click" indicator-position="none" :interval="5000" height="250px" >
        <ElCarouselItem  v-for="(item, index) in fantasticAdminInfo.data" :key="item">
          <ElImage :src="item" fit="contain" style="cursor: pointer; width:100%; height: 100%; margin:auto;transform-origin: left;" @click="fantasticAdminInfo.imageVisible = true; fantasticAdminInfo.index = index" />
        </ElCarouselItem>
      </ElCarousel>
      <ElImageViewer v-if="fantasticAdminInfo.imageVisible" :url-list="fantasticAdminInfo.data" :initial-index="fantasticAdminInfo.index" @close="fantasticAdminInfo.imageVisible = false" />
    </PageMain>

    <div class="w-full flex flex-col gap-4 px-4 xl:flex-row">
      <PageMain class="ecology">
        <template #title>
          <div class="title-info">
            <img src="https://picss.sunbangyan.cn/2024/01/10/fc61cd2a93bafbba14671c518d402695.jpeg">
            <div>
              <h1 class="c-[#143856]">
                For Student
              </h1>
              <h2 style="color: #999;">面向学生提供最真实的求职数据</h2>
            </div>
          </div>
        </template>
        <ul v-for="item in oneStepStudentInfo.feature" :key="item" >
          <li>
            <el-text type="info" size="large">
              {{ item }}
            </el-text>
          </li>
        </ul>
      </PageMain>
      <PageMain class="ecology">
        <template #title>
          <div class="title-info">
            <img src="https://picss.sunbangyan.cn/2024/01/10/3bee76f7173b65922e38ae348a7d8a59.jpeg">
            <div>
              <h1 class="c-[#4e6273]">
                For School
              </h1>
              <el-text type="info" size="large">
                面向学校提供最坚固的校企沟通桥梁
              </el-text>
<!--              <h2  style="color: #999;">面向学校提供最坚固的校企沟通桥梁</h2>-->
            </div>
          </div>
        </template>
        <ul v-for="item in oneStepSchoolInfo.feature" :key="item" >
          <li>
            <el-text type="info" size="large">
              {{ item }}
            </el-text>
          </li>
        </ul>
      </PageMain>
      <PageMain class="ecology">
        <template #title>
          <div class="title-info">
            <img src="https://picss.sunbangyan.cn/2024/01/10/76d8deb49fe69f4c69e4fb5bb5589077.jpeg">
            <div>
              <h1 class="c-[#a7b2bc]">
                For Enterprise
              </h1>
              <h2 style="color: #999;">面向企业提供最高质量的求职者</h2>
            </div>
          </div>
        </template>
        <ul v-for="item in oneStepHRInfo.feature" :key="item" >
          <li>
            <el-text type="info" size="large">
              {{ item }}
            </el-text>
          </li>
        </ul>
      </PageMain>
    </div>


  </div>
</template>
<style lang="scss" scoped>
.lighter-font {
  font-family: "等线 Light", Arial, sans-serif;
  //font-size: 25px;
  color: #1e1e25; /* 使用浅灰色作为字体颜色 */
}
.custom-font {

}
.text-emphasis {
  text-emphasis-style: "❤";
}


.ecology {
  --at-apply: flex-1 m-0;

  :deep(.title-container) {
    --at-apply: flex items-center justify-between flex-wrap gap-4;

    .title-info {
      --at-apply: flex items-center gap-4;

      img {
        --at-apply: block w-12 h-12;
      }

      h1 {
        --at-apply: m-0 text-2xl;
      }

      h2 {
        --at-apply: m-0 text-base text-stone-5 font-normal;
      }
    }

  }
}

.question {
  .answer {
    --at-apply: m-0 pl-6 text-stone-5;

    li {
      --at-apply: mb-2 lh-6 ;last:mb-0;
    }

    span {
      --at-apply: text-stone-7; dark:text-stone-3 font-bold;
    }

  }
}
</style>
