// ph:tree-structure
import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

// 定义路由对象
const routes: RouteRecordRaw = {
  // 路由路径
  path: '/domain',

  // 使用的布局组件
  component: Layout,
  // 重定向到子路由'/user/index'
  redirect: '/domain/major',
  // 路由名称
  name: 'domain',
  // 路由元信息
  meta: {
    // 页面标题
    title: '数据维度',

    // 页面图标，这里使用了一个名为 'ep:user' 的图标
    icon: 'ph:tree-structure',
  },
  // 子路由数组
  children: [
    {
      // 子路由路径
      path: 'major',
      // 子路由名称
      name: 'Major',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/admin/domain/major.vue'),
      // 子路由元信息
      meta: {
        // 子页面标题
        title: '专业',
        // 在侧边栏中隐藏该子路由
        // sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        // breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/domain/major',
        icon: 'mdi:peanut-outline',
      },
    },
    {
      // 子路由路径
      path: 'direction',
      // 子路由名称
      name: 'Direction',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/admin/domain/direction.vue'),
      // 子路由元信息
      // component: (resolve) => require(['@/views/admin/domain/direction.vue'],resolve),
      meta: {
        // 子页面标题
        title: '就业方向',
        // 在侧边栏中隐藏该子路由
        // sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        // breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/domain/direction',
        icon: 'teenyicons:direction-outline',
      },
    },
    {
      // 子路由路径
      path: 'pairing',
      // 子路由名称
      name: 'Pairing',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/admin/domain/pairing.vue'),
      // 子路由元信息
      // component: (resolve) => require(['@/views/admin/domain/direction.vue'],resolve),
      meta: {
        // 子页面标题
        title: '关系配对',
        // 在侧边栏中隐藏该子路由
        // sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        // breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/domain/pairing',
        icon: 'tabler:relation-many-to-many',
      },
    },
    {
      // 子路由路径
      path: 'company',
      // 子路由名称
      name: 'Corp',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/admin/domain/company.vue'),
      // 子路由元信息
      // component: (resolve) => require(['@/views/admin/domain/direction.vue'],resolve),
      meta: {
        // 子页面标题
        title: '公司管理',
        // 在侧边栏中隐藏该子路由
        // sidebar: false,
        // // 在面包屑导航中隐藏该子路由
        // breadcrumb: false,
        // // 激活状态对应的菜单路径，用于高亮侧边栏菜单
        activeMenu: '/domain/company',
        icon: 'icon-park-outline:building-one',
      },
    },
  ],
}

export default routes
