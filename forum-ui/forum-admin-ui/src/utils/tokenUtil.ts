// 管理token
const ADMIN_TOKEN: string = 'ADMIN_TOKEN'

/**
 * 从本地存储中获取token
 */
export const getLocalToken = () => {
  return localStorage.getItem(ADMIN_TOKEN)
}

/**
 * 从本地存储中设置token
 * @param token
 */
export const setLocalToken = (token: string) => {
  localStorage.setItem(ADMIN_TOKEN, token)
}

/**
 * 移除本地存储中的token
 */
export const removeLocalToken = () => {
  localStorage.removeItem(ADMIN_TOKEN)
}

/**
 * 从sessionStorage中获取token
 */
export const getSessionToken = () => {
  return sessionStorage.getItem(ADMIN_TOKEN)
}

/**
 * 从sessionStorage中设置token
 * @param token
 */
export const setSessionToken = (token: string) => {
  sessionStorage.setItem(ADMIN_TOKEN, token)
}

/**
 * 移除sessionStorage中的token
 */
export const removeSessionToken = () => {
  sessionStorage.removeItem(ADMIN_TOKEN)
}
