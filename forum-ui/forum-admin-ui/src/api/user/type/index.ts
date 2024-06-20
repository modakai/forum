// 用户请求参数

import type { ResponseData } from '@/api/types'

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

//定义登录接口返回数据类型
export interface LoginResponseData extends ResponseData {
  data: string
}
