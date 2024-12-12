import useSettingsStore from './settings'
import useRouteStore from './route'
import useMenuStore from './menu'
import router from '@/router'
import apiUser from '@/api/modules/user'
import apitest from '@/api/modules/test'
import {ElMessage} from 'element-plus'
const useUserStore = defineStore(
  // 唯一ID
  'user',
  () => {
    const settingsStore = useSettingsStore()
    const routeStore = useRouteStore()
    const menuStore = useMenuStore()

    const account = ref(localStorage.account ?? '')
    const token = ref(localStorage.token ?? '')
    //console.log(token)
    const failure_time = ref(localStorage.failure_time ?? '')
    const role = ref(localStorage.role ?? '')
    const permissions = ref<string[]>([])
    const isLogin = computed(() => {
      let retn = false
      //console.log(token.value)
      if (token.value) {
        if (new Date().getTime() < Number.parseInt(failure_time.value) * 1000) {
          retn = true
        }
      }
      return retn
    })

    // 登录
    async function login(data: {
      account: string
      password: string
    }) {
      const res = await apiUser.login(data)
      //console.log(res)
      if(res.data==null){
        ElMessage.error("账号或密码错误")
      }
      let data1 = []
        data1.push(
          {
            account:res.data.ID,
            role:res.data.roleID,
            token:`${res.data.ID}_${res.data.mID}_${res.data.roleID}_${res.data.cID}_@string`,
            failure_time: Math.ceil(new Date().getTime() / 1000) + 24 * 60 * 60,
          }
        )

      //console.log(data1)
      localStorage.setItem('account', data1[0].account)
      localStorage.setItem('token', data1[0].token)
      // @ts-ignore
      localStorage.setItem('failure_time', data1[0].failure_time)
      localStorage.setItem('role', data1[0].role)

      //console.log(localStorage.token)
      account.value = data1[0].account
      token.value = data1[0].token
      //console.log(token)
      failure_time.value = data1[0].failure_time
      role.value = data1[0].role
    }
    // 登出
    async function logout(redirect = router.currentRoute.value.fullPath) {
      window.location.reload();
      localStorage.removeItem('account')
      localStorage.removeItem('token')
      localStorage.removeItem('failure_time')
      localStorage.removeItem('role')
      account.value = ''
      token.value = ''
      failure_time.value = ''
      role.value = ''
      permissions.value = []
      routeStore.removeRoutes()
      menuStore.setActived(0)
      router.push({
        name: 'login',
        query: {
          ...(router.currentRoute.value.path !== settingsStore.settings.home.fullPath && router.currentRoute.value.name !== 'login' && { redirect }),
        },
      })
    }
    // 获取权限
    async function getPermissions() {
      const res = await apiUser.permission()
      console.log(res)
      permissions.value = res.data.permissions
      console.log(permissions.value)
      return permissions.value
    }
    // 修改密码
    async function editPassword(data: {
      password: string
      newpassword: string
    }) {
      await apiUser.passwordEdit(data)
    }

    async function newStudent(
      data: {
        id: string
        name: string
        major: string
        telephone: string
        status: string
      }) {
      await apitest.newStudent(data)
    }
    return {
      account,
      token,
      role,
      permissions,
      isLogin,
      login,
      logout,
      getPermissions,
      editPassword,
      newStudent,
    }
  },
)

export default useUserStore
