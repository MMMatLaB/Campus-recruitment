import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

const routes: RouteRecordRaw = {
  path: '/role',
  component: Layout,
  redirect: '/role/index',
  name: 'Role',
  meta: {
    title: '角色管理',
    icon: 'clarity:group-line',
  },
  children: [
    {
      path: 'index',
      name: 'RoleTable',
      component: () => import('@/views/admin/role/index.vue'),
      meta: {
        title: '角色管理',
        sidebar: false,
        breadcrumb: false,
        activeMenu: '/role',
      },
    },
  ],
}

export default routes
