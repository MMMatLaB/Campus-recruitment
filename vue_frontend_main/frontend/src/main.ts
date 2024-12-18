import '@/utils/baidu'

import '@/utils/system.copyright'

import FloatingVue from 'floating-vue'
import 'floating-vue/dist/style.css'

import Message from 'vue-m-message'
import 'vue-m-message/dist/style.css'

import VXETable from 'vxe-table'
import 'vxe-table/lib/style.css'
// import VueWriter from "vue-writer"
import 'overlayscrollbars/overlayscrollbars.css'
// import 'vue-writer'
import App from './App.vue'
import pinia from './store'
import router from './router'
import ui from './ui-provider'
// import VForm3 from 'vform3-builds'
// 自定义指令
import directive from '@/utils/directive'
import Typewriter from 'vue-typewriter'
import VueWriter from 'vue-writer'

// 加载 svg 图标
import 'virtual:svg-icons-register'

// 加载 iconify 图标
import { downloadAndInstall } from '@/iconify'
import icons from '@/iconify/index.json'

import 'virtual:uno.css'

// 全局样式
import '@/assets/styles/globals.scss'

const app = createApp(App)
app.use(FloatingVue, {
  distance: 12,
})
app.use(Message)
app.use(VXETable)
app.use(pinia)
app.use(router)
app.use(Typewriter)
app.use(VueWriter)
// app.use(VueWriter)
app.use(ui)
// app.use(VForm3)
directive(app)
if (icons.isOfflineUse) {
  for (const info of icons.collections) {
    downloadAndInstall(info)
  }
}

app.mount('#app')
