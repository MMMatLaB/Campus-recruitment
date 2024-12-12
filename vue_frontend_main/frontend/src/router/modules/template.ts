import type { RouteRecordRaw } from 'vue-router'

function Layout() {
  return import('@/layouts/index.vue')
}

const routes: RouteRecordRaw = {
  path: '/template',
  component: Layout,
  redirect: '/template/index',
  name: 'Template',
  meta: {
    title: '模板',
    icon: 'ic:twotone-double-arrow',
  },
  children: [
    {
      path: 'index',
      name: 'TemplateIndex',
      component: () => import('@/views/template/index.vue'),
      meta: {
        title: '模板ood',
      },
    },
  ],
}

export default routes
