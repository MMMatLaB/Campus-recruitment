<template>
  <TransitionRoot as="template" :appear="appear" :show="isOpen">
    <Dialog class="fixed inset-0 z-2000 flex" @close="!preventClose && close()">
      <TransitionChild as="template" :appear="appear" v-bind="overlayTransitionClass">
        <div class="fixed inset-0 bg-stone-2/75 transition-opacity dark:bg-stone-8/75" :class="{ 'backdrop-blur-sm': overlay }" />
      </TransitionChild>
      <div class="fixed inset-0 overflow-y-auto">
        <div class="min-h-full flex items-end justify-center p-4 text-center sm:items-center sm:p-0">
          <TransitionChild as="template" :appear="appear" v-bind="transitionClass">
            <DialogPanel class="relative w-full max-w-3xl flex flex-col overflow-hidden rounded-xl bg-white text-left shadow-xl sm:my-8 dark:bg-stone-8">
              <div flex="~ items-center justify-between" px-4 py-3 border-b="~ solid stone/15" text-6>
                <DialogTitle m-0 text-lg text-dark dark:text-white>
                  {{ title }}
                </DialogTitle>
                <SvgIcon name="carbon:close" cursor-pointer @click="close" />
              </div>
              <DialogDescription class="overflow-auto" m-0 p-4>
                <!-- 使用 slot 传递职位、薪资、就业地点等信息 -->
                  <!-- Job Title -->
                <el-col>
                  <el-row justify="space-between">
                    <el-col :span="20" >
                      <el-row >
                        <h1>{{ positionTitle }}&nbsp;
                        </h1>
                      </el-row>
                      <!--                            <el-divider border-style="none" direction="horizontal"></el-divider>-->
                      <el-row >
                        <el-col>
                          <el-space >
                            <!--                                <el-text type="info" size="default">{{ Curjob.company }}</el-text>-->
                            <el-tag size="large" type="success">{{ jobLocation }}</el-tag>
                            <el-tag size="large" >{{ educationRequirement }}</el-tag>
                            <el-tag size="large" type="warning">{{ workExperience }}</el-tag>
                          </el-space>
                        </el-col>
                      </el-row>
                    </el-col>
                    <el-col :span="4" >
                      <!--                            <el-divider border-style="none" direction="horizontal"></el-divider>-->
                      <div>&nbsp;</div>
                      <h2><span style="color:orangered;font-family: 'Andale Mono'">{{salary}}</span></h2>
<!--                      <el-button  plain type="primary" size="large" @click="handle(Curjob)">投递简历</el-button>-->
                      <!--                            <el-divider border-style="none" direction="horizontal"></el-divider>-->
                    </el-col>
                  </el-row>
                </el-col>
<!--                  <el-row class="job-title">-->
<!--                    <el-col :span="12">-->
<!--                      <h2>{{ positionTitle }}</h2>-->
<!--                    </el-col>-->
<!--                    <el-col :span="12">-->
<!--                      <h2 class="salary-tag">{{ salary }}</h2>-->
<!--                    </el-col>-->
<!--                  </el-row>-->

<!--                  &lt;!&ndash; Job Information &ndash;&gt;-->
<!--                  <el-row class="job-info">-->
<!--                    <el-col :span="48">-->
<!--                      <el-tag type="success" STYLE="font-size: medium">{{ jobLocation }}</el-tag>-->
<!--                      <el-tag STYLE="font-size: medium" >{{ educationRequirement }}</el-tag>-->
<!--                      <el-tag type="warning" STYLE="font-size: medium">{{ workExperience }}</el-tag>-->
<!--                    </el-col>-->
<!--                  </el-row>-->

                  <!-- Job Description -->
                  <el-row>
                    <el-col :span="24">
                      <el-divider></el-divider>
                      <div>
                        <el-scrollbar height="300px" >
                          <strong class="intro-label">职位描述：</strong>
                          <!--                        <h5>职位描述：</h5>-->
                          <div class="intro-content">
                            <div v-html="formattedJobIntro"></div>
                          </div>
                        </el-scrollbar>
                      </div>

<!--                        <strong class="intro-label">职位简介：</strong>-->
<!--                        <h5>职位描述：</h5>-->
<!--                        <div class="intro-content">-->
<!--                          <div v-html="formattedJobIntro"></div>-->
<!--                        </div>-->
<!--                      </div>-->
                    </el-col>
                  </el-row>
              </DialogDescription>
              <div v-if="!!slots.footer" flex="~ items-center justify-end" px-4 py-3 border-t="~ solid stone/15">
                <slot name="footer" />
              </div>
            </DialogPanel>
          </TransitionChild>
        </div>
      </div>
    </Dialog>
  </TransitionRoot>
</template>

<script setup lang="ts">
import { Dialog, DialogDescription, DialogPanel, DialogTitle, TransitionChild, TransitionRoot } from '@headlessui/vue'
import { defineProps, ref, computed, defineEmits, withDefaults, useSlots } from 'vue'

const props = withDefaults(
  defineProps<{
    modelValue: boolean
    appear?: boolean
    title?: string
    preventClose?: boolean
    overlay?: boolean
    positionTitle: string
    salary: string
    jobLocation: string
    educationRequirement: string
    workExperience:string,
    jobIntroText:string
  }>(),
  {
    modelValue: false,
    appear: false,
    preventClose: false,
    overlay: false,
    jobIntroText:'',


  },
)

const emits = defineEmits<{
  'update:modelValue': [boolean]
  'close': []
}>()

const slots = useSlots()

const overlayTransitionClass = ref({
  enter: 'ease-in-out duration-500',
  enterFrom: 'opacity-0',
  enterTo: 'opacity-100',
  leave: 'ease-in-out duration-500',
  leaveFrom: 'opacity-100',
  leaveTo: 'opacity-0',
})

const transitionClass = computed(() => {
  return {
    enter: 'ease-out duration-300',
    enterFrom: 'opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95',
    enterTo: 'opacity-100 translate-y-0 sm:scale-100',
    leave: 'ease-in duration-200',
    leaveFrom: 'opacity-100 translate-y-0 sm:scale-100',
    leaveTo: 'opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95',
  }
})

const isOpen = computed({
  get() {
    return props.modelValue
  },
  set(value) {
    emits('update:modelValue', value)
  },
})

function close() {
  isOpen.value = false
  emits('close')
}

// 格式化岗位简介，将换行符替换为 <br> 标签
const formattedJobIntro = computed(() => props.jobIntroText.replace(/\n/g, '<br>'));
</script>
<style>

.job-title {
  padding: 10px 20px;
  background-color: #409EFF;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.salary-tag {
  //font-size: 20px;
  color: #ffffff;
}

.job-info {
  padding: 20px;
}

.el-tag {
  margin-right: 8px;
  margin-bottom: 8px;
}

.job-description {
  margin-top: 15px;
}

.intro-label {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 8px;
}

.intro-content {
  line-height: 1.6;
}
</style>
