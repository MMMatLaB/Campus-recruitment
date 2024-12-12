import { defaultsDeep } from 'lodash-es'
import type { RecursiveRequired, Settings } from '#/global'
import settingsDefault from '@/settings.default'

const globalSettings: Settings.all = {
  // app: {
  //   enablePermission: true,
  //   enableDynamicTitle: true,
  // },
  // layout: {
  //   enableMobileAdaptation: true,
  // },
  // menu: {
  //   enableSubMenuCollapseButton: true,
  //   enableHotkeys: true,
  // },
  // topbar: {
  //   mode: 'fixed',
  // },
  // toolbar: {
  //   enableFullscreen: true,
  //   enablePageReload: true,
  //   enableColorScheme: true,
  // },
  // mainPage: {
  //   enableHotkeys: true,
  // },
  // copyright: {
  //   enable: true,
  //   dates: '2023',
  //   company: 'CodeGPT',
  //   // website: 'https://fantastic-admin.gitee.io',
  // },
  // app: {
  //   enablePermission: true,
  //   enableDynamicTitle: true
  // },
  // layout: {
  //   enableMobileAdaptation: true
  // },
  // menu: {
  //   enableSubMenuCollapseButton: true,
  //   enableHotkeys: true,
  //   menuMode: "head",
  //   subMenuCollapse: true
  // },
  // topbar: {
  //   mode: "fixed"
  // },
  // toolbar: {
  //   enableFullscreen: true,
  //   enablePageReload: true,
  //   enableColorScheme: true
  // },
  // copyright: {
  //   enable: true,
  //   dates: "2023",
  //   company: "CodeBot"
  // }

  app: {
  enablePermission: true,
      enableDynamicTitle: true
},
  layout: {
  enableMobileAdaptation: true
},
  menu: {
  enableSubMenuCollapseButton: true,
      enableHotkeys: true,
      menuMode: "head"
},
  topbar: {
  mode:"fixed"
},
  toolbar: {
  enableFullscreen: true,
      enablePageReload: true,
      enableColorScheme: true
},
  copyright: {
  enable: true,
      dates: "2023",
      company: "CodeBot"
}

}

export default defaultsDeep(globalSettings, settingsDefault) as RecursiveRequired<Settings.all>
