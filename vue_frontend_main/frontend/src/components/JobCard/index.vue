<!--<template>-->
<!--  <el-card class="job-card">-->
<!--    <el-row class="job-title">-->
<!--      <el-col :span="12">-->
<!--        <h2>{{ positionTitle }}</h2>-->
<!--      </el-col>-->
<!--      <el-col :span="12">-->
<!--      <h2>{{salary}}</h2>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--    <el-row class="job-info">-->
<!--      <el-col :span="24">-->
<!--        <el-tag>{{ jobLocation }}</el-tag>-->
<!--        <el-tag>{{ educationRequirement }}</el-tag>-->
<!--        <el-tag>{{ workExperience }}</el-tag>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--    <el-row>-->
<!--      <el-col :span="24">-->
<!--        <el-divider></el-divider>-->
<!--        <div class="job-description">-->
<!--          <strong class="intro-label">职位简介：</strong>-->
<!--          <div class="intro-content">-->
<!--            {{ jobIntroText }}-->
<!--            <slot name="job-intro"></slot>-->
<!--          </div>-->
<!--        </div>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--  </el-card>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  props: {-->
<!--    positionTitle: String,-->
<!--    salary: String,-->
<!--    jobLocation: String,-->
<!--    educationRequirement: String,-->
<!--    workExperience: String,-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      jobIntroText: '这是一份令人激动的工作，我们期待你的加入！',-->
<!--    };-->
<!--  },-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.job-card {-->
<!--  width: 400px;-->
<!--  margin: 20px;-->
<!--}-->

<!--.job-title {-->
<!--  padding: 10px 20px;-->
<!--  background-color: #409EFF;-->
<!--  color: #fff;-->
<!--}-->

<!--.salary-tag {-->
<!--  font-size: 20px;-->
<!--}-->

<!--.job-info {-->
<!--  padding: 20px;-->
<!--}-->

<!--.el-tag {-->
<!--  margin-right: 8px;-->
<!--  margin-bottom: 8px;-->
<!--}-->

<!--.job-description {-->
<!--  margin-top: 15px;-->
<!--}-->

<!--.intro-label {-->
<!--  font-size: 16px;-->
<!--  font-weight: bold;-->
<!--  margin-bottom: 8px;-->
<!--}-->

<!--.intro-content {-->
<!--  line-height: 1.6;-->
<!--}-->
<!--</style>-->
<template>
  <el-card class="job-details-dialog">
    <!-- Job Title -->
    <el-row class="job-title">
      <el-col :span="12">
        <h2>{{ positionTitle }}</h2>
      </el-col>
      <el-col :span="12">
        <h2 class="salary-tag">{{ salary }}</h2>
      </el-col>
    </el-row>

    <!-- Job Information -->
    <el-row class="job-info">
      <el-col :span="24">
        <el-tag>{{ jobLocation }}</el-tag>
        <el-tag>{{ educationRequirement }}</el-tag>
        <el-tag>{{ workExperience }}</el-tag>
      </el-col>
    </el-row>

    <!-- Job Description -->
    <el-row>
      <el-col :span="24">
        <el-divider></el-divider>
        <div class="job-description">
          <strong class="intro-label">职位简介：</strong>
          <div class="intro-content">
            {{ jobIntroText }}
            <slot name="job-intro"></slot>
          </div>
        </div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  props: {
    positionTitle: String,
    salary: String,
    jobLocation: String,
    educationRequirement: String,
    workExperience: String,
  },
  data() {
    return {
      jobIntroText: '这是一份令人激动的工作，我们期待你的加入！',
    };
  },
};
</script>

<style scoped>
.job-details-dialog {
  width: 400px;
  margin: 20px;
}

.job-title {
  padding: 10px 20px;
  background-color: #409EFF;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.salary-tag {
  font-size: 20px;
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
