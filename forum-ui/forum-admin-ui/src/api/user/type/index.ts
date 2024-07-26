// 用户请求参数

import type { ResponseData } from '@/api/types'
import type { UserInfo } from '@/store/modules/types/types'

/**
 * 密码登入的请求参数接口
 */
export interface NormalLoginForm {
  username: string
  password: string
  captcha: string
  loginType: 'normal'
  // 密码登入的验证码key
  key: string
  rememberMe: boolean
}

/**
 * 短信登入的请求参数接口
 */
export interface SmsLoginForm {
  username: string
  captcha: string
  loginType: 'sms'
  rememberMe: boolean
}

/**
 * 更新密码
 */
export interface ChangePasswordForm {
  oldPassword: string
  newPassword: string
}

/**
 * 更新用户信息
 */
export interface ChangeProfileForm {
  nickName: string
  gender: boolean
  phone: string
}

//定义登录接口返回数据类型
export interface LoginResponseData extends ResponseData {
  data: string
}

// 定义获取用户信息返回得数据类型
export interface UserInfoResponseData extends ResponseData {
  data: {
    user: UserInfo
    roles: string[]
    permissions: string[]
  }
}
