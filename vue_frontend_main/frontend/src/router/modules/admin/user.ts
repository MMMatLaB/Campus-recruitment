import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

// 定义路由对象
const routes: RouteRecordRaw = {
  // 路由路径
  path: '/user',
  // 使用的布局组件
  component: Layout,
  // 重定向到子路由'/user/index'
  redirect: '/user/stu',
  // 路由名称
  name: 'User',
  // 路由元信息
  meta: {
    // 页面标题
    title: '用户管理',

    // 页面图标，这里使用了一个名为 'ep:user' 的图标
    icon: 'ep:user',
  },
  // 子路由数组
  children: [
    {
      // 子路由路径
      path: 'stu',
      // 子路由名称
      name: 'stuUser',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/admin/user/student.vue'),
      // 子路由元信息
      meta: {
        // 子页面标题
        title: '学生用户',
        // 在侧边栏中隐藏该子路由
        // sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        // breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/user/stu',
        icon: 'ph:student-fill',
      },
    }, {
      path: 'edu',
      name: 'eduUser',
      component: () => import('@/views/admin/user/school.vue'),
      meta: {
        title: '学校用户',
        // sidebar: false,
        // breadcrumb: false,
        activeMenu: '/user/edu',
        icon: 'teenyicons:school-outline',
      },
    }, {
      path: 'hr',
      name: 'hrUser',
      component: () => import('@/views/admin/user/company.vue'),
      meta: {
        title: '企业用户',
        // sidebar: false,
        // breadcrumb: false,
        activeMenu: '/user/hr',
        icon: 'clarity:building-line',
      },
    },
  ],
}

export default routes
