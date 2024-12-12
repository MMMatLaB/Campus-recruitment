import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

// 定义路由对象
const routes: RouteRecordRaw = {
  // 路由路径
  path: '/monitor',

  // 使用的布局组件
  component: Layout,
  // 重定向到子路由'/user/index'
  redirect: '/monitor/sql',
  // 路由名称
  name: 'Monitor',
  // 路由元信息
  meta: {
    // 页面标题
    title: '系统监控',

    // 页面图标，这里使用了一个名为 'ep:user' 的图标
    icon: 'icon-park:monitor-two',
  },
  // 子路由数组
  children: [
    // {
    //   // 子路由路径
    //   path: 'sql',
    //   // 子路由名称
    //   name: 'sql',
    //   // 使用懒加载方式引入组件，提高性能
    //   component: () => import('@/views/admin/user/index.vue'),
    //   // 子路由元信息
    //   meta: {
    //     // 子页面标题
    //     title: 'SQL监控',
    //     // 在侧边栏中隐藏该子路由
    //     // sidebar: false,
    //     // // 在面包屑导航中隐藏该子路由
    //     // breadcrumb: false,
    //     // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
    //     activeMenu: '/monitor/sql',
    //     icon: 'fontisto:mysql',
    //   },
    // },
    {
      path: 'hadoop',
      name: 'hadoop',
      component: () => import('@/views/admin/monitor/hadoop.vue'),
      meta: {
        title: 'Hadoop监控',
        // sidebar: false,
        // breadcrumb: false,
        activeMenu: '/monitor/hadoop',
        icon: 'simple-icons:apachehadoop',
      },
    }, {
      path: 'hive',
      name: 'hive',
      component: () => import('@/views/admin/monitor/hive.vue'),
      meta: {
        title: 'Hive监控',
        // sidebar: false,
        // breadcrumb: false,
        activeMenu: '/monitor/hive',
        icon: 'simple-icons:apachehive',
      },
    },
  ],
}

export default routes
