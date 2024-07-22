// 登入接口
import type {
  LoginResponseData,
  NormalLoginForm,
  SmsLoginForm,
  UserInfoResponseData
} from '@/api/user/type'
import request from '@/utils/request'

//项目用户相关的请求地址
enum API {
  LOGIN_URL = '/system/login',

  USERINFO_URL = '/system/getInfo',

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

/**
 * 获取用户信息
 */
export const getUserInfo = () => {
  return request.get<any, UserInfoResponseData>(API.USERINFO_URL)
}
