import { defineStore } from 'pinia'
import type { UserState } from '@/store/modules/types/types'
import type { NormalLoginForm, SmsLoginForm } from '@/api/user/type'
import { setLocalToken, setSessionToken } from '@/utils/tokenUtil'

/**
 * 用户状态仓库
 */
const useUserStore = defineStore('forum-sys-user', {
  state(): UserState {
    return {
      // 记住我标识
      rememberMe: true,
      // 用户信息
      userInfo: {},
      // token
      token: ''
    }
  },
  actions: {
    /**
     * 登入请求
     * @param formData 登入参数
     */
    async login(formData: NormalLoginForm | SmsLoginForm) {
      // 根据传递过来的rememberMe 做事情
      this.rememberMe = formData.rememberMe

      // 模拟登入成功
      console.log('登入请求')
      // rememberMe = true 就是持久化存储 token
      this.token = 'token'
      if (this.rememberMe) {
        setLocalToken(this.token)
      } else {
        setSessionToken(this.token)
      }
      return new Promise((resolve, reject) => {
        resolve(true)
      })
    }
  }
})

export default useUserStore
