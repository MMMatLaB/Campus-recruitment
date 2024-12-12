// simple-icons:apifox
import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

// 定义路由对象
const routes: RouteRecordRaw = {
  // 路由路径
  path: '/apidoc',
  component: Layout,
  // 重定向到子路由'/user/index'
  redirect: '/apidoc/index',
  name: 'apidoc',
  meta: {
    // 页面标题
    title: '接口文档',

    // 页面图标，这里使用了一个名为 'ep:user' 的图标
    icon: 'simple-icons:apifox',
  },
  // 子路由数组
  children: [
    {
      path: 'index',
      name: 'apidocIndex',
      // 使用懒加载方式引入组件，提高性能
      component: () => import('@/views/admin/apidoc/index.vue'),
      meta: {
        title: '接口文档',
        sidebar: false,
        breadcrumb: false,
        activeMenu: '/apidoc',
      },
    },
  ],
}

export default routes
