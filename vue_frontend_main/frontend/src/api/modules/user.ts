import api from '../index'
import {ElMessage} from "element-plus";

export default {
  // 登录

  login: (data: {
    account: string
    password: string
  }) => api.post('/login', data, {

  }),

  // 获取权限
  permission: () => api.get('user/permission', {
    baseURL: '/mock/',
  }),
  // 获取usrid和roleid
  getInfo: () => api.get('user/info',{
    baseURL: '/mock/',
  }),

  // 修改密码
  passwordEdit: (data: {
    password: string
    newpassword: string
  }) => api.post('user/password/edit', data, {
    baseURL: '/mock/',
  }),
}
