import type { ResponseData } from '@/api/types'

export interface Route {
  name: string
  path: string
  component: string
  meta: {
    title: string
    icon: string
    visible: boolean
    isExternalLink: boolean
  }
  children: Route[]
}

// 获取路由组件的响应结果
export interface RoutesResponseData extends ResponseData {
  data: Route[]
}
