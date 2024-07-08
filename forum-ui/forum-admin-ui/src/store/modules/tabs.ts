import { defineStore } from 'pinia'
import type { RouteRecord } from 'vue-router'
import type { TabStore } from '@/store/modules/types/types'
import type { TabPaneName } from 'element-plus'

export const useTabsStore = defineStore('tabs', {
  state(): TabStore {
    return {
      tabPans: [
        {
          label: '首页',
          path: '/'
        }
      ]
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
    },
    /**
     * 删除已经存在 tab中的路由
     * @param path 删除标识
     */
    remove(path: TabPaneName) {
      this.tabPans = this.tabPans.filter((item) => item.path !== path)
    }
  }
})
