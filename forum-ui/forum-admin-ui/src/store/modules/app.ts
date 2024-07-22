import { defineStore } from 'pinia'
import { getLocalStorage, setLocalStorage } from '@/utils/util'

const IS_DARK_LOCAL_KEY = 'isDark'
const COLLAPSED_LOCAL_KEY = 'isCollapsed'
const FULL_LOCAL_KEY = 'isFull'

export const useAppStore = defineStore('app', {
  state() {
    return {
      collapsed: JSON.parse(getLocalStorage(COLLAPSED_LOCAL_KEY) as string) || true,
      isDark: JSON.parse(getLocalStorage(IS_DARK_LOCAL_KEY) as string) || false,
      isFull: JSON.parse(getLocalStorage(FULL_LOCAL_KEY) as string) || false
    }
  },
  actions: {
    toggleCollapsed() {
      this.collapsed = !this.collapsed
      setLocalStorage(COLLAPSED_LOCAL_KEY, JSON.stringify(this.collapsed))
    },
    toggleDark() {
      this.isDark = !this.isDark
      setLocalStorage(IS_DARK_LOCAL_KEY, JSON.stringify(this.isDark))
      if (this.isDark) {
        document.documentElement.classList.add('dark')
      } else {
        document.documentElement.classList.remove('dark')
      }
    },
    toggleFull() {
      this.isFull = !this.isFull
      setLocalStorage(FULL_LOCAL_KEY, JSON.stringify(this.isFull))
      if (this.isFull) {
        // 开启全屏
        document.documentElement.requestFullscreen()
      } else {
        // 关闭全屏
        document.exitFullscreen()
      }
    },
    initTheme() {
      if (this.isDark) {
        document.documentElement.classList.add('dark')
      } else {
        document.documentElement.classList.remove('dark')
      }
    },
    setTitle(title: string) {
      document.title = title
    }
  }
})
