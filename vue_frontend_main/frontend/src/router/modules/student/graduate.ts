// simple-icons:apifox
import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

// 定义路由对象
const routes: RouteRecordRaw = {
  // 路由路径
  path: '/graduateStu',

  // 使用的布局组件
  component: Layout,
  // 重定向到子路由'/user/index'
  redirect: '/graduateStu/info',
  // 路由名称
  name: 'graduateStu',
  // 路由元信息
  meta: {
    // 页面标题
    title: '毕业去向',

    // 页面图标，这里使用了一个名为 'ep:user' 的图标
    icon: 'game-icons:graduate-cap',
  },
  // 子路由数组
  children: [
    {
      // 子路由路径
      path: 'info',
      // 子路由名称
      name: 'graduateStuInfo',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/student/graduate/graduateInfo.vue'),
      // 子路由元信息
      meta: {
        title: '往年信息',
        activeMenu: '/graduateStu/info',
        icon: 'icon-park-outline:table-report',
      },
    },{
      // 子路由路径
      path: 'preview/:id',
      // 子路由名称
      name: 'graduateStuInfoPreview',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/student/graduate/preview.vue'),
      // 子路由元信息
      meta: {
        // 子页面标题
        title: '就业报告',
        // 在侧边栏中隐藏该子路由
        sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        // breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/graduateStu/info',
        // icon: 'lets-icons:setting-line-light',
      },
    },
  ],
}
export default routes
