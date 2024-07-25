/**
 * 权限验证
 */
import router from './router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/tokenUtil'
import { useUserStore } from '@/store/modules/sysUser'
import { useAppStore } from '@/store/modules/app'
import { usePermissionStore } from '@/store/modules/permission'
import { isRelogin } from '@/utils/request'
import { ElMessage } from 'element-plus'
import { isHttp } from '@/utils/util'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login', '/auth-redirect', '/bind', '/register']

router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) {
    to.meta.title && useAppStore().setTitle(to.meta.title as string)
    // 如果存在token 无法跳转登入页
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done()
    } else {
      // 如果不是去登入页
      if (useUserStore().roles.length === 0) {
        isRelogin.show = true
        // 判断是否拉起完用户信息
        useUserStore()
          .getInfo()
          .then(() => {
            isRelogin.show = false
            usePermissionStore()
              .generateRoutes()
              .then((accessRoutes: any) => {
                // roles权限生成可访问的路由表
                accessRoutes.forEach((route: any) => {
                  if (!isHttp(route.path)) {
                    // 动态添加可访问路由表
                    router.addRoute(route)
                  }
                })
                // hack方法 确保addRoutes已完成
                next({ ...to, replace: true })
              })
          })
          .catch(async (err) => {
            ElMessage.error(err)
            next({ path: '/' })
          })
      } else {
        next()
      }
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})
