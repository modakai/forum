// 创建axios实例
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { getToken } from '@/utils/tokenUtil'

// 是否显示重新登入
export const isRelogin = { show: false }

export const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  // 超时
  timeout: 10000 * 20
})

// request拦截器
service.interceptors.request.use(
  (config) => {
    // 配置请求头token
    if (getToken()) {
      config.headers['Authorization-ADMIN'] = 'Bearer ' + getToken()
    }
    return config
  },
  (error) => {
    console.log(error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (res) => {
    return res.data
  },
  (error) => {
    console.log('err', error)
    let { msg } = error
    if (msg == 'Network Error') {
      msg = '后端接口连接异常'
    } else if (msg.includes('timeout')) {
      msg = '系统接口请求超时'
    } else if (msg.includes('Request failed with status code')) {
      msg = '系统接口' + msg.substr(msg.length - 3) + '异常'
    }
    ElMessage({
      message: msg,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
