export interface UserInfo {
  id: number
  username: string
  nickName: string
  avatar: string
  gender: string

  [propName: string]: any
}

export interface UserState {
  // 记住我标识
  rememberMe: boolean
  // 用户信息
  userInfo: UserInfo
  // token
  token: string
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
