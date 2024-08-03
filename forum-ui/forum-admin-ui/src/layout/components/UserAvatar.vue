<script lang="ts" setup>
import { useTabsStore, useUserStore } from '@/store'
import router from '@/router'

const tabsStore = useTabsStore()
const errorHandler = () => true
const DEFAULT_AVATAR = '@/src/assets/images/img.png'
const dropdownList = [
  {
    label: '个人中心',
    icon: 'user',
    path: '/profile',
    click() {
      // 跳转个人中心页
      router.push(this.path)
      // 添加到tabPens
      let routeRecord = {
        path: this.path,
        meta: {
          title: this.label
        }
      }
      tabsStore.addTab(routeRecord)
    }
  },
  {
    label: '退出登录',
    icon: 'logout',
    path: '/login',
    click() {
      // 发送退出登入请求
      // 清除用户信息
      // 跳转登入页
    }
  }
]

// 获取用户信息
const userStore = useUserStore()
const userInfo = userStore.userInfo
</script>

<template>
  <el-dropdown class="avatar" style="line-height: normal" trigger="hover">
    <div class="cursor-pointer mr-16 flex" style="align-items: center">
      <el-avatar
        :size="36"
        :src="userInfo.avatar ? userInfo.avatar : DEFAULT_AVATAR"
        @error="errorHandler"
      />
    </div>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item v-for="(item, index) in dropdownList" :key="index" @click="item.click()">
          {{ item.label }}
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<style lang="scss" scoped>
.userInfo {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex-shrink: 0;
  margin-left: 12px;

  span {
    &:first-child {
      font-size: 14px;
    }

    &:last-child {
      font-size: 12px;
    }
  }
}
</style>
