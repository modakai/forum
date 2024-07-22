import { defineStore } from 'pinia'
import { constantsRoutes, Layout } from '@/router'
import { getRoutes } from '@/api/menu'

// 导入view中得全部组件
const modules = import.meta.glob('@/views/**/*.vue')

export const usePermissionStore = defineStore('permission', {
  state: () => ({
    routes: [] as any,
    addRoutes: [] as any,
    defaultRoutes: [] as any,
    sidebarRouters: [] as any,
    topbarRouters: [] as any
  }),
  actions: {
    setRoutes(routes: any) {
      this.addRoutes = routes
      this.routes = constantsRoutes.concat(routes)
    },
    setDefaultRoutes(routes: any) {
      this.defaultRoutes = constantsRoutes.concat(routes)
    },
    setTopbarRoutes(routes: any) {
      this.topbarRouters = routes
    },
    setSidebarRouters(routes: any) {
      this.sidebarRouters = routes
    },
    generateRoutes(routes?: string[]) {
      // 发起获取路由列表得请求
      return getRoutes().then((res) => {
        if (res.code === 200) {
          // 封装路由组件
          const data = res.data
          const sdata = JSON.parse(JSON.stringify(data))
          const rdata = JSON.parse(JSON.stringify(data))
          const defaultData = JSON.parse(JSON.stringify(data))
          const sidebarRoutes = filterAsyncRouter(sdata)
          const rewriteRoutes = filterAsyncRouter(rdata, false, true)
          const defaultRoutes = filterAsyncRouter(defaultData)
          // const asyncRoutes = filterDynamicRoutes(dynamicRoutes)
          this.setRoutes(rewriteRoutes)
          this.setSidebarRouters(constantsRoutes.concat(sidebarRoutes))
          this.setDefaultRoutes(sidebarRoutes)
          this.setTopbarRoutes(defaultRoutes)

          return Promise.resolve(rewriteRoutes)
        }
      })
    }
  }
})

// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter(asyncRouterMap: any[], lastRouter = false, type = false) {
  return asyncRouterMap.filter((route) => {
    if (type && route.children) {
      route.children = filterChildren(route.children)
    }
    if (route.component) {
      // Layout ParentView 组件特殊处理
      // if (route.component === 'Layout') {
      //   route.component = Layout
      // } else if (route.component === 'ParentView') {
      //   route.component = ParentView
      // } else if (route.component === 'InnerLink') {
      //   route.component = InnerLink
      // } else {
      //   route.component = loadView(route.component)
      // }
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        route.component = loadView(route.component)
      }
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children, route, type)
    } else {
      delete route['children']
      delete route['redirect']
    }
    return true
  })
}

function filterChildren(childrenMap: any[], lastRouter?: any) {
  let children: any[] = []
  // @typescript-eslint/no-unused-vars
  childrenMap.forEach((el, index) => {
    if (el.children && el.children.length) {
      if (el.component === 'ParentView' && !lastRouter) {
        el.children.forEach((c: any) => {
          c.path = el.path + '/' + c.path
          if (c.children && c.children.length) {
            children = children.concat(filterChildren(c.children, c))
            return
          }
          children.push(c)
        })
        return
      }
    }
    if (lastRouter) {
      el.path = lastRouter.path + '/' + el.path
    }
    children = children.concat(el)
  })
  return children
}

// 动态路由遍历，验证是否具备权限
// export function filterDynamicRoutes(routes: any) {
//   const res: any[] = []
//   routes.forEach((route: any) => {
//     if (route.permissions) {
//       if (auth.hasPermiOr(route.permissions)) {
//         res.push(route)
//       }
//     } else if (route.roles) {
//       if (auth.hasRoleOr(route.roles)) {
//         res.push(route)
//       }
//     }
//   })
//   return res
// }

export const loadView = (view: any) => {
  let res
  for (const path in modules) {
    const dir = path.split('views/')[1].split('.vue')[0]
    if (dir === view) {
      res = () => modules[path]()
    }
  }
  return res
}
