//项目用户相关的请求地址
import request from '@/utils/request'
import type { RoutesResponseData } from '@/api/menu/type'

enum API {
  GET_ROUTES = '/system/getRouters'
}

// 获取路由
export const getRoutes = () => {
  return request.get<any, RoutesResponseData>(API.GET_ROUTES)
}
