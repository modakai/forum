import { defineStore } from 'pinia'
import type { RouteRecord } from 'vue-router'
import type { TabStore } from '@/store/modules/types/types'
import type { TabPaneName } from 'element-plus'
import { getSessionStorage, setSessionStorage } from '@/utils/util'

const TAB_PANS_KEY = 'forum-pans'
const INIT_TAB_PANS = [
  {
    label: '首页',
    path: '/home'
  }
]

export const useTabsStore = defineStore('tabs', {
  state(): TabStore {
    return {
      tabPans: getSessionStorage(TAB_PANS_KEY) || INIT_TAB_PANS
    }
  },
  actions: {
    /**
     *
     * @param route 路由对象
     */
    addTab(route: RouteRecord) {
      // 判断是否以及添加过 如果没有添加过则直接添加
      if (this.tabPans.some((item) => item.path === route.path)) {
        return
      }
      this.tabPans.push({
        label: route.meta.title as string,
        path: route.path
      })
      // 会话缓存
      setSessionStorage(TAB_PANS_KEY, this.tabPans)
    },
    /**
     * 删除已经存在 tab中的路由
     * @param path 删除标识
     */
    remove(path: TabPaneName) {
      this.tabPans = this.tabPans.filter((item) => item.path !== path)
    },
    /**
     * 初始化头部 tab-pans列表
     */
    initTabPans() {
      let cacheTabPans = getSessionStorage(TAB_PANS_KEY)
      // 如果cacheTabPans不为空 则赋值给 tabPans
      if (cacheTabPans) {
        this.tabPans = cacheTabPans
      } else {
        this.tabPans = INIT_TAB_PANS
      }
    }
  }
})
