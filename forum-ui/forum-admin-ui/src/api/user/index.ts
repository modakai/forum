// 登入接口
import type { LoginResponseData, NormalLoginForm, SmsLoginForm } from '@/api/user/type'
import request from '@/utils/request'

//项目用户相关的请求地址
enum API {
  LOGIN_URL = '/system/login',
  // LOGIN_URL = '/api/user/login',

  USERINFO_URL = '/admin/acl/index/info',
  // USERINFO_URL = '/api/user/info',

  LOGOUT_URL = '/admin/acl/index/logout'
}

/**
 * 登入方法
 *  登入成功返回 token
 * @param { NormalLoginForm | SmsLoginForm } formData
 */
export const login = (formData: NormalLoginForm | SmsLoginForm) => {
  return request.post<any, LoginResponseData>(API.LOGIN_URL, formData)
}
