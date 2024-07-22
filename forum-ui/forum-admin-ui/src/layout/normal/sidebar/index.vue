<script lang="ts" name="Sidebar" setup>
import SideLogo from '@/layout/components/SideLogo.vue'
import SideMenu from '@/layout/components/SideMenu.vue'
import { useAppStore, usePermissionStore } from '@/store'
import { useRoute } from 'vue-router'
import { computed } from 'vue'
// 获取路由菜单
const appStore = useAppStore()
// 路由
const route = useRoute()
// 菜单路由
const permissionStore = usePermissionStore()

const sidebarRouters = computed(() => permissionStore.sidebarRouters)
console.log('菜单列表', sidebarRouters.value)
</script>

<template>
  <div class="sidebar">
    <!--  Logo区域  -->
    <SideLogo class="side-logo" />
    <!--  菜单  -->
    <!--  菜单区域  -->
    <el-scrollbar class="menu_scrollbar" style="margin-top: 4px">
      <el-menu
        :collapse="!appStore.collapsed"
        :default-active="$route.path"
        class="menu-items"
        unique-opened
      >
        <SideMenu :menu-list="sidebarRouters" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<style lang="scss" scoped>
.side-logo {
  border-bottom: 1px solid var(--el-border-color-light);
}

.menu_scrollbar {
  width: 100%;
  height: calc(100vh - 60px);
}
</style>
