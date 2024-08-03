export interface UserInfo {
  id: number
  username: string
  nickName: string
  avatar: string
  gender: boolean

  [propName: string]: any
}

export interface UserState {
  // 记住我标识
  rememberMe: boolean
  // 用户信息
  userInfo: UserInfo
  // token
  token: string
  // 角色列表 限制为字符串数组
  roles: string[]
  // 权限列表
  permissions: string[]
}

/**   tab的约束 **/
export interface TabStore {
  tabPans: TabPanes
}

export interface TabPane {
  label: string
  path: string
}

export type TabPanes = TabPane[]
