// simple-icons:apifox
import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

// 定义路由对象
const routes: RouteRecordRaw = {
  // 路由路径
  path: '/questionnaire_management',

  // 使用的布局组件
  component: Layout,
  // 重定向到子路由'/user/index'
  redirect: '/questionnaire_management/upload',
  // 路由名称
  name: 'questionnaireManagement',
  // 路由元信息
  meta: {
    // 页面标题
    title: '问卷调研',

    // 页面图标，这里使用了一个名为 'ep:user' 的图标
    icon: 'tdesign:questionnaire',
  },
  // 子路由数组
  children: [
    {
      // 子路由路径
      path: 'upload',
      // 子路由名称
      name: 'QuestionnaireManagementUpload',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/school/questionnaire/upload.vue'),
      // 子路由元信息
      meta: {
        // 子页面标题
        title: '问卷设计',
        activeMenu: '/questionnaire_management/upload',
        icon: 'wpf:paper-plane',
      },
    },
    {
      // 子路由路径
      path: 'info',
      // 子路由名称
      name: 'QuestionnaireManagementInfo',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/school/questionnaire/manage.vue'),
      // 子路由元信息
      meta: {
        // 子页面标题
        title: '问卷管理',
        // 在侧边栏中隐藏该子路由
        // sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        // breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/questionnaire_management/info',
        icon: 'lets-icons:setting-line-light',
      },
    },
    {
      // 子路由路径
      path: 'preview/:id',
      // 子路由名称
      name: 'QuestionnaireManagementPreview',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/school/questionnaire/preview.vue'),
      // 子路由元信息
      meta: {
        // 子页面标题
        title: '问卷设计',
        activeMenu: '/questionnaire_management/info',
        sidebar: false,
        breadcrumb: false,
        // icon: 'wpf:paper-plane',
      },
    },
  ],
}

export default routes
