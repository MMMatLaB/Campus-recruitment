// mingcute:necktie-line
// simple-icons:apifox
import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

// 定义路由对象
const routes: RouteRecordRaw = {
  // 路由路径
  path: '/talent_pool',

  // 使用的布局组件
  component: Layout,
  // 重定向到子路由'/user/index'
  redirect: '/talent_pool/index',
  // 路由名称
  name: 'talentPool',
  // 路由元信息
  meta: {
    // 页面标题
    title: '人才库',

    // 页面图标，这里使用了一个名为 'ep:user' 的图标
    icon: 'ic:twotone-warehouse',
  },
  // 子路由数组
  children: [
    {
      // 子路由路径
      path: 'index',
      // 子路由名称
      name: 'TalentPool',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/hr/pool.vue'),
      // 子路由元信息
      meta: {
        // 子页面标题
        // title: '校招岗位',
        // 在侧边栏中隐藏该子路由
        sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/talent_pool',
        // icon: 'ic:twotone-warehouse',
      },
    },
  ],
}

export default routes
