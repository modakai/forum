import { defineStore } from 'pinia'
import type { UserInfo, UserState } from '@/store/modules/types/types'
import type { NormalLoginForm, SmsLoginForm } from '@/api/user/type'
import { removeLocalToken, removeSessionToken, setLocalToken, setSessionToken } from '@/utils/tokenUtil'
import { getUserInfo, login } from '@/api/user'

/**
 * 用户状态仓库
 */
export const useUserStore = defineStore('forum-sys-user', {
  state(): UserState {
    return {
      // 记住我标识
      rememberMe: false,
      // 用户信息
      userInfo: {} as UserInfo,
      // token
      token: '',
      // 角色列表
      roles: [],
      // 权限列表
      permissions: []
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
      const response = await login(formData)
      if (response.code === 200) {
        // rememberMe = true 就是持久化存储 token
        this.token = response.data
        if (this.rememberMe) {
          setLocalToken(this.token)
        } else {
          setSessionToken(this.token)
        }
        return true
      } else {
        return Promise.reject(new Error(response.msg))
      }
    },
    /**
     * 获取用户信息
     */
    getInfo() {
      // 发送获取用户信息请求
      return getUserInfo()
        .then((response) => {
          if (response.code === 200) {
            this.userInfo = response.data.user
            if (response.data.roles && response.data.roles.length > 0) {
              this.roles = response.data.roles
              this.permissions = response.data.permissions
            } else {
              this.roles = ['ROLE_DEFAULT']
            }
          }
          return Promise.resolve(response)
        })
        .catch((error) => {
          return Promise.reject(error)
        })
    },
    logout() {},
    clearToken() {
      removeSessionToken()
      removeLocalToken()
    },
    changeProfile(data: any) {
      // 遍历 data 的所有属性
      for (const key in data) {
        // 检查 userInfo 是否有相同的属性
        if (Object.prototype.hasOwnProperty.call(this.userInfo, key)) {
          // 更新 userInfo 的相应属性
          this.userInfo[key] = data[key]
        }
      }
    },
    changeAvatar(url: string) {
      this.userInfo.avatar = url
    }
  }
})
