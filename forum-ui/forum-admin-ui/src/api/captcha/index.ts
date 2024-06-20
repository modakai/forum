// 获取验证码
import request from '@/utils/request'
import type { ResponseData } from '@/api/types'

interface CaptchaResponse extends ResponseData {
  data: string
}

/**
 * 获取验证码
 * @param key key （normal 传递 key，sms传递username）
 * @param type 类型
 */
export const getCaptcha = (key: string, type: string) => {
  return request.get<any, CaptchaResponse>(`/captcha?key=${key}&type=${type}`)
}
