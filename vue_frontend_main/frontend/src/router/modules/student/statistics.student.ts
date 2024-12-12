// simple-icons:apifox
import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

// 定义路由对象
const routes: RouteRecordRaw = {
  // 路由路径
  path: '/student_statistics',

  // 使用的布局组件
  component: Layout,
  // 重定向到子路由'/user/index'
  redirect: '/student_statistics/major',
  // 路由名称
  name: 'studentStatistics',
  // 路由元信息
  meta: {
    // 页面标题
    title: '数据分析',

    // 页面图标，这里使用了一个名为 'ep:user' 的图标
    icon: 'ep:data-analysis',
  },
  // 子路由数组
  children: [
    {
      // 子路由路径
      path: 'major',
      // 子路由名称
      name: 'MajorStatistics',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/student/statistics/major.vue'),
      // 子路由元信息
      meta: {
        // 子页面标题
        title: '专业',
        // 在侧边栏中隐藏该子路由
        // sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        // breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/student_statistics/major',
        icon: 'mdi:peanut-outline',
      },
    },
    {
      // 子路由路径
      path: 'direction',
      // 子路由名称
      name: 'DirectionStatistics',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/student/statistics/direction.vue'),
      // 子路由元信息
      meta: {
        // 子页面标题
        title: '就业方向',
        // 在侧边栏中隐藏该子路由
        // sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        // breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/student_statistics/direction',
        icon: 'teenyicons:direction-outline',
      },
    },
  ],
}

export default routes
