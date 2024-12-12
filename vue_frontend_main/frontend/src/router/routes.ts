import { setupLayouts } from 'virtual:meta-layouts'
import generatedRoutes from 'virtual:generated-pages'
import type { RouteRecordRaw } from 'vue-router'
import MultilevelMenuExample from './modules/multilevel.menu.example'
import BreadcrumbExample from './modules/breadcrumb.example'
import KeepAliveExample from './modules/keep.alive.example'
import ComponentExample from './modules/component.example'
import IconExample from './modules/icon.example'
import Template from './modules/template'
import FeatureExample from './modules/feature.example'
import PluginExample from './modules/plugin.example'
import PermissionExample from './modules/permission.example'
import MockExample from './modules/mock.example'
import JsxExample from './modules/jsx.example'
import ExternalLinkExample from './modules/external.link.example'
import UserManagement from './modules/admin/user.ts'
import RoleManagement from './modules/admin/role.ts'
import type { Route } from '#/global'
import useSettingsStore from '@/store/modules/settings'
import Monitor from '@/router/modules/admin/monitor.ts'
import Permissition from '@/router/modules/admin/permission.ts'
import Dictionary from '@/router/modules/admin/dictionary.ts'
import Log from '@/router/modules/admin/log.ts'
import Domain from '@/router/modules/admin/domain.ts'
import Apidoc from '@/router/modules/admin/apidoc.ts'
import QuestionnaireManage from '@/router/modules/school/questionnaire.manage.ts'
import Graduate from '@/router/modules/school/graduate.ts'
import StudentManagement from '@/router/modules/school/student.management.ts'
import StatisticsSchool from '@/router/modules/school/statistics.school.ts'
import StatisticsStudent from '@/router/modules/student/statistics.student.ts'
import GraduateStu from '@/router/modules/student/graduate.ts'
import Job from '@/router/modules/student/job.ts'
import JobManagement from '@/router/modules/company/job.manage.ts'
import Talentpool from '@/router/modules/company/Talentpool.ts'
import Questionnaire from "@/router/modules/student/questionnaire.ts";
// 固定路由（默认路由）
const constantRoutes: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login.vue'),
    meta: {
      title: '登录',
    },
  },
  {
    path: '/:all(.*)*',
    name: 'notFound',
    component: () => import('@/views/[...all].vue'),
    meta: {
      title: '找不到页面',
    },
  },
]

// 系统路由
const systemRoutes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('@/layouts/index.vue'),
    meta: {
      title: () => useSettingsStore().settings.home.title,
      breadcrumb: false,
    },
    children: [
      {
        path: '',
        component: () => import('@/views/index.vue'),
        meta: {
          title: () => useSettingsStore().settings.home.title,
          icon: 'ant-design:home-twotone',
          breadcrumb: false,
        },
      },
      {
        path: 'reload',
        name: 'reload',
        component: () => import('@/views/reload.vue'),
        meta: {
          title: '重新加载',
          breadcrumb: false,
        },
      },
      {
        path: 'personal/setting',
        name: 'personalSetting',
        component: () => import('@/views/personal/setting.vue'),
        meta: {
          title: '个人设置',
          cache: false,
          breadcrumb: false,
        }
      }
      // },
      // {
      //   path: 'personal/edit/password',
      //   name: 'personalEditPassword',
      //   component: () => import('@/views/personal/edit.password.vue'),
      //   meta: {
      //     title: '修改密码',
      //   },
      // },
    ],
  },
]

// 动态路由（异步路由、导航栏路由）
const asyncRoutes: Route.recordMainRaw[] = [


  {
    meta: {
      auth:['permission.student'],
      title: '学生',
      icon: 'ph:student-fill',
    },
    children: [
      // Template,
      StatisticsStudent,
      GraduateStu,
      Job,
      Questionnaire,
    ],

  },
  {
    meta: {
      auth:['permission.school'],
      title: '学校',
      icon: 'teenyicons:school-outline',
    },
    children: [
      // Template,
      StatisticsSchool,
      // StudentManagement,
      Graduate,
      QuestionnaireManage,
    ],
  },

  {
    meta: {
      auth:['permission.company'],
      title: '企业',
      icon: 'clarity:building-line',
    },
    children: [
      // Log,
      JobManagement,
      Talentpool,
    ],
  },
  {
    meta: {
      auth:['permission.admin'],
      title: '管理员',
      icon: 'eos-icons:admin-outlined',
    },
    children: [
      UserManagement,
      RoleManagement,
      // Permissition,
      Domain,
      Dictionary,
      Apidoc,
      // Log,
      Monitor,
    ],
  }
  // ,
  // {
  //   meta: {
  //     title: '演示',
  //     icon: 'uim:box',
  //   },
  //   children: [
  //     // UserManagement,
  //     // RoleManagement,
  //     MultilevelMenuExample,
  //     BreadcrumbExample,
  //     KeepAliveExample,
  //     ComponentExample,
  //     IconExample,
  //     FeatureExample,
  //     PluginExample,
  //     PermissionExample,
  //     MockExample,
  //     JsxExample,
  //     ExternalLinkExample,
  //     Template,
  //   ],
  // },
]

const constantRoutesByFilesystem = generatedRoutes.filter((item) => {
  return item.meta?.enabled !== false && item.meta?.constant === true
})

const asyncRoutesByFilesystem = setupLayouts(generatedRoutes.filter((item) => {
  return item.meta?.enabled !== false && item.meta?.constant !== true && item.meta?.layout !== false
}))

export {
  constantRoutes,
  systemRoutes,
  asyncRoutes,
  constantRoutesByFilesystem,
  asyncRoutesByFilesystem,
}
